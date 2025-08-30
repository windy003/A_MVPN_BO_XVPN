package com.myvpn.simple;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.VpnService;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.myvpn.simple.xray.XrayConfig;
import com.myvpn.simple.xray.TrojanSettings;
import cn.gov.xivpn2.LibXivpn;
import cn.gov.xivpn2.service.XiVPNService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimpleVPNService extends XiVPNService {
    private static final String TAG = "SimpleVPNService";
    private static final String CHANNEL_ID = "VPN_CHANNEL";
    private static final int NOTIFICATION_ID = 1;
    private static final int SOCKS_PORT = 18964;
    
    public enum Status {
        DISCONNECTED,
        CONNECTING, 
        CONNECTED
    }
    
    private Status currentStatus = Status.DISCONNECTED;
    private ParcelFileDescriptor vpnInterface;
    private TrojanConfig currentConfig;
    private final IBinder binder = new VPNBinder();
    private final Set<VPNStatusListener> listeners = new HashSet<>();
    
    public interface VPNStatusListener {
        void onStatusChanged(Status status);
        void onMessage(String message);
    }
    
    public class VPNBinder extends Binder {
        public SimpleVPNService getService() {
            return SimpleVPNService.this;
        }
        
        public Status getStatus() {
            return currentStatus;
        }
        
        public void addListener(VPNStatusListener listener) {
            listeners.add(listener);
        }
        
        public void removeListener(VPNStatusListener listener) {
            listeners.remove(listener);
        }
        
        public void connect(TrojanConfig config) {
            startVPN(config);
        }
        
        public void disconnect() {
            stopVPN();
        }
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();
            if ("CONNECT".equals(action)) {
                TrojanConfig config = (TrojanConfig) intent.getSerializableExtra("config");
                if (config != null) {
                    startVPN(config);
                }
            } else if ("DISCONNECT".equals(action)) {
                stopVPN();
            }
        }
        return START_STICKY;
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                "VPN Service",
                NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
    
    private synchronized void startVPN(TrojanConfig config) {
        if (currentStatus != Status.DISCONNECTED) {
            return;
        }
        
        this.currentConfig = config;
        updateStatus(Status.CONNECTING);
        
        try {
            // 建立VPN接口
            if (!establishVPN()) {
                updateStatus(Status.DISCONNECTED);
                notifyMessage("VPN接口建立失败");
                return;
            }
            
            // 构建Xray配置
            XrayConfig xrayConfig = buildXrayConfig(config);
            
            // 启动libxivpn
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String configJson = gson.toJson(xrayConfig);
            Log.i(TAG, "Xray配置: " + configJson);
            
            String result = LibXivpn.xivpn_start(
                configJson, 
                SOCKS_PORT, 
                vpnInterface.getFd(), 
                "", // 不需要日志文件
                getFilesDir().getAbsolutePath(), 
                this
            );
            
            if (!result.isEmpty()) {
                Log.e(TAG, "libxivpn启动失败: " + result);
                updateStatus(Status.DISCONNECTED);
                notifyMessage("代理启动失败: " + result);
                try {
                    vpnInterface.close();
                } catch (IOException e) {
                    Log.e(TAG, "关闭VPN接口失败", e);
                }
                return;
            }
            
            updateStatus(Status.CONNECTED);
            notifyMessage("VPN连接成功: " + config.server);
            
        } catch (Exception e) {
            Log.e(TAG, "VPN启动失败", e);
            updateStatus(Status.DISCONNECTED);
            notifyMessage("VPN连接异常: " + e.getMessage());
        }
    }
    
    private boolean establishVPN() {
        try {
            Builder builder = new Builder();
            builder.setMtu(1500);
            builder.addAddress("10.89.64.1", 32);
            builder.addRoute("0.0.0.0", 0);
            builder.addRoute("::", 0);
            builder.addDnsServer("8.8.8.8");
            builder.addDnsServer("8.8.4.4");
            
            try {
                String appName = getPackageManager().getApplicationLabel(getApplicationInfo()).toString();
                builder.setSession(appName);
            } catch (Exception e) {
                builder.setSession("Simple VPN");
            }
            
            vpnInterface = builder.establish();
            if (vpnInterface == null) {
                return false;
            }
            
            startForeground(NOTIFICATION_ID, createNotification("VPN已连接"));
            
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Failed to establish VPN", e);
            return false;
        }
    }
    
    private XrayConfig buildXrayConfig(TrojanConfig config) {
        XrayConfig xrayConfig = new XrayConfig();
        xrayConfig.inbounds = new ArrayList<>();
        xrayConfig.outbounds = new ArrayList<>();
        
        // 创建SOCKS5入站
        XrayConfig.Inbound socks5Inbound = new XrayConfig.Inbound();
        socks5Inbound.protocol = "socks";
        socks5Inbound.port = SOCKS_PORT;
        socks5Inbound.listen = "10.89.64.1";
        socks5Inbound.settings = new HashMap<>();
        socks5Inbound.settings.put("udp", true);
        
        // 配置嗅探
        socks5Inbound.sniffing = new XrayConfig.Sniffing();
        socks5Inbound.sniffing.enabled = true;
        socks5Inbound.sniffing.destOverride = List.of("http", "tls");
        socks5Inbound.sniffing.routeOnly = true;
        
        xrayConfig.inbounds.add(socks5Inbound);
        
        // 创建Trojan出站
        XrayConfig.Outbound trojanOutbound = new XrayConfig.Outbound();
        trojanOutbound.tag = "proxy";
        trojanOutbound.protocol = "trojan";
        
        TrojanSettings trojanSettings = new TrojanSettings();
        trojanSettings.servers.add(new TrojanSettings.TrojanServer(
            config.server, config.port, config.password));
        trojanOutbound.settings = trojanSettings;
        
        // 配置TLS
        trojanOutbound.streamSettings = new XrayConfig.StreamSettings();
        trojanOutbound.streamSettings.network = "tcp";
        trojanOutbound.streamSettings.security = "tls";
        trojanOutbound.streamSettings.tlsSettings = new XrayConfig.TlsSettings();
        // 临时使用服务器 IP 作为 SNI，忽略配置中的 SNI
        trojanOutbound.streamSettings.tlsSettings.serverName = config.server;
        trojanOutbound.streamSettings.tlsSettings.allowInsecure = true;
        
        xrayConfig.outbounds.add(trojanOutbound);
        
        // 创建直连出站
        XrayConfig.Outbound directOutbound = new XrayConfig.Outbound();
        directOutbound.tag = "direct";
        directOutbound.protocol = "freedom";
        directOutbound.settings = new HashMap<>();
        xrayConfig.outbounds.add(directOutbound);
        
        // 创建路由规则
        xrayConfig.routing = new XrayConfig.Routing();
        xrayConfig.routing.rules = new ArrayList<>();
        
        XrayConfig.RoutingRule rule = new XrayConfig.RoutingRule();
        rule.type = "field";
        rule.outboundTag = "proxy";
        rule.network = "tcp,udp";
        xrayConfig.routing.rules.add(rule);
        
        return xrayConfig;
    }
    
    
    private synchronized void stopVPN() {
        if (currentStatus == Status.DISCONNECTED) {
            return;
        }
        
        // 停止libxivpn
        try {
            LibXivpn.xivpn_stop();
        } catch (Exception e) {
            Log.e(TAG, "停止libxivpn失败", e);
        }
        
        // 关闭VPN接口
        if (vpnInterface != null) {
            try {
                vpnInterface.close();
            } catch (IOException e) {
                Log.e(TAG, "关闭VPN接口失败", e);
            }
            vpnInterface = null;
        }
        
        updateStatus(Status.DISCONNECTED);
        stopForeground(true);
        notifyMessage("VPN已断开");
        
        stopSelf();
    }
    
    private void updateStatus(Status status) {
        currentStatus = status;
        for (VPNStatusListener listener : listeners) {
            listener.onStatusChanged(status);
        }
    }
    
    private void notifyMessage(String message) {
        Log.i(TAG, message);
        for (VPNStatusListener listener : listeners) {
            listener.onMessage(message);
        }
    }
    
    private Notification createNotification(String text) {
        Intent intent = new Intent(this, MainActivity.class);
        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flags |= PendingIntent.FLAG_IMMUTABLE;
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(
            this, 0, intent, flags
        );
        
        return new NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Simple VPN")
            .setContentText(text)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build();
    }
    
    @Override
    public void onRevoke() {
        Log.i(TAG, "VPN权限被撤销");
        stopVPN();
        super.onRevoke();
    }
    
    @Override
    public void onDestroy() {
        stopVPN();
        super.onDestroy();
    }
}