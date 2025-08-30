package com.myvpn.simple;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.VpnService;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.myvpn.simple.database.NodeDatabase;
import com.myvpn.simple.database.TrojanNode;
import com.myvpn.simple.database.TrojanNodeDao;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int VPN_REQUEST_CODE = 1;
    private static final int NODES_REQUEST_CODE = 2;
    
    private TextView tvStatus;
    private TextView tvMessage;
    private TextView tvServerInfo;
    private Button btnPasteSubscription;
    private Button btnManageNodes;
    private Button btnConnect;
    
    private SimpleVPNService.VPNBinder vpnBinder;
    private TrojanConfig currentConfig;
    private boolean isServiceBound = false;
    private TrojanNodeDao nodeDao;
    
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            vpnBinder = (SimpleVPNService.VPNBinder) service;
            isServiceBound = true;
            
            updateUI(vpnBinder.getStatus());
            
            vpnBinder.addListener(new SimpleVPNService.VPNStatusListener() {
                @Override
                public void onStatusChanged(SimpleVPNService.Status status) {
                    runOnUiThread(() -> updateUI(status));
                }
                
                @Override
                public void onMessage(String message) {
                    runOnUiThread(() -> tvMessage.setText(message));
                }
            });
        }
        
        @Override
        public void onServiceDisconnected(ComponentName name) {
            vpnBinder = null;
            isServiceBound = false;
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();
        initDatabase();
        bindVPNService();
        loadSelectedNode();
    }
    
    private void initViews() {
        tvStatus = findViewById(R.id.tv_status);
        tvMessage = findViewById(R.id.tv_message);
        tvServerInfo = findViewById(R.id.tv_server_info);
        btnPasteSubscription = findViewById(R.id.btn_paste_subscription);
        btnManageNodes = findViewById(R.id.btn_manage_nodes);
        btnConnect = findViewById(R.id.btn_connect);
        
        btnPasteSubscription.setOnClickListener(v -> pasteSubscription());
        btnManageNodes.setOnClickListener(v -> openNodeManager());
        btnConnect.setOnClickListener(v -> toggleVPN());
    }
    
    private void initDatabase() {
        NodeDatabase database = NodeDatabase.getInstance(this);
        nodeDao = database.trojanNodeDao();
    }
    
    private void loadSelectedNode() {
        new Thread(() -> {
            TrojanNode selectedNode = nodeDao.getSelectedNode();
            runOnUiThread(() -> {
                if (selectedNode != null) {
                    currentConfig = selectedNode.toConfig();
                    tvServerInfo.setText("当前节点: " + selectedNode.getDisplayName());
                    btnConnect.setEnabled(true);
                } else {
                    currentConfig = null;
                    tvServerInfo.setText("请选择节点");
                    btnConnect.setEnabled(false);
                }
            });
        }).start();
    }
    
    private void openNodeManager() {
        Intent intent = new Intent(this, NodesActivity.class);
        startActivityForResult(intent, NODES_REQUEST_CODE);
    }
    
    private void bindVPNService() {
        Intent intent = new Intent(this, SimpleVPNService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
    
    private void pasteSubscription() {
        List<TrojanConfig> configs = ClipboardHelper.readSubscriptionFromClipboard(this);
        
        if (configs.isEmpty()) {
            Toast.makeText(this, R.string.no_subscription, Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (configs.size() == 1) {
            // 单个节点，直接使用
            currentConfig = configs.get(0);
            if (!currentConfig.isValid()) {
                Toast.makeText(this, R.string.invalid_subscription, Toast.LENGTH_SHORT).show();
                currentConfig = null;
                return;
            }
            tvServerInfo.setText("临时节点: " + currentConfig.server + ":" + currentConfig.port);
            btnConnect.setEnabled(true);
            Toast.makeText(this, "临时节点已加载", Toast.LENGTH_SHORT).show();
        } else {
            // 多个节点，引导用户到节点管理页面
            Toast.makeText(this, "发现多个节点，请前往节点管理页面导入", Toast.LENGTH_LONG).show();
            openNodeManager();
        }
    }
    
    private void toggleVPN() {
        if (!isServiceBound || vpnBinder == null) {
            Toast.makeText(this, "服务未就绪", Toast.LENGTH_SHORT).show();
            return;
        }
        
        SimpleVPNService.Status status = vpnBinder.getStatus();
        
        if (status == SimpleVPNService.Status.CONNECTED) {
            vpnBinder.disconnect();
        } else if (status == SimpleVPNService.Status.DISCONNECTED) {
            if (currentConfig == null) {
                Toast.makeText(this, "请先粘贴订阅链接", Toast.LENGTH_SHORT).show();
                return;
            }
            
            Intent intent = VpnService.prepare(this);
            if (intent != null) {
                startActivityForResult(intent, VPN_REQUEST_CODE);
            } else {
                connectVPN();
            }
        }
    }
    
    private void connectVPN() {
        if (vpnBinder != null && currentConfig != null) {
            vpnBinder.connect(currentConfig);
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == VPN_REQUEST_CODE && resultCode == RESULT_OK) {
            connectVPN();
        } else if (requestCode == VPN_REQUEST_CODE) {
            Toast.makeText(this, "VPN权限被拒绝", Toast.LENGTH_SHORT).show();
        } else if (requestCode == NODES_REQUEST_CODE && resultCode == RESULT_OK) {
            loadSelectedNode();
        }
    }
    
    private void updateUI(SimpleVPNService.Status status) {
        switch (status) {
            case DISCONNECTED:
                tvStatus.setText(R.string.status_disconnected);
                btnConnect.setText(R.string.connect);
                btnConnect.setEnabled(currentConfig != null);
                btnPasteSubscription.setEnabled(true);
                break;
                
            case CONNECTING:
                tvStatus.setText(R.string.status_connecting);
                btnConnect.setText(R.string.connect);
                btnConnect.setEnabled(false);
                btnPasteSubscription.setEnabled(false);
                break;
                
            case CONNECTED:
                tvStatus.setText(R.string.status_connected);
                btnConnect.setText(R.string.disconnect);
                btnConnect.setEnabled(true);
                btnPasteSubscription.setEnabled(false);
                break;
        }
    }
    
    @Override
    protected void onDestroy() {
        if (isServiceBound) {
            unbindService(serviceConnection);
        }
        super.onDestroy();
    }
}