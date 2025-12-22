package com.myvpn.simple;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u0000 02\u00020\u0001:\u00040123B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0002J\u0014\u0010\u0013\u001a\u00020\u00142\n\u0010\u0015\u001a\u00060\u0016R\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0014H\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u001bH\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\u0014H\u0016J\b\u0010&\u001a\u00020\u0014H\u0016J\b\u0010\'\u001a\u00020\u0014H\u0016J\"\u0010(\u001a\u00020)2\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020)H\u0016J\u0010\u0010,\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\bH\u0002J\b\u0010-\u001a\u00020\u0014H\u0002J\u0010\u0010.\u001a\u00020\u00142\u0006\u0010/\u001a\u00020\nH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00060\u0006R\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00064"}, d2 = {"Lcom/myvpn/simple/SimpleVPNService;", "Lcn/gov/xivpn2/service/XiVPNService;", "()V", "appExclusionManager", "Lcom/myvpn/simple/ui/AppExclusionManager;", "binder", "Lcom/myvpn/simple/SimpleVPNService$VPNBinder;", "currentConfig", "Lcom/myvpn/simple/TrojanConfig;", "currentStatus", "Lcom/myvpn/simple/SimpleVPNService$Status;", "listeners", "", "Lcom/myvpn/simple/SimpleVPNService$VPNStatusListener;", "vpnInterface", "Landroid/os/ParcelFileDescriptor;", "buildXrayConfig", "Lcom/myvpn/simple/xray/XrayConfig;", "config", "configureAppExclusion", "", "builder", "Landroid/net/VpnService$Builder;", "Landroid/net/VpnService;", "createNotification", "Landroid/app/Notification;", "text", "", "createNotificationChannel", "establishVPN", "", "notifyMessage", "message", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onRevoke", "onStartCommand", "", "flags", "startId", "startVPN", "stopVPN", "updateStatus", "status", "Companion", "Status", "VPNBinder", "VPNStatusListener", "app_debug"})
public final class SimpleVPNService extends cn.gov.xivpn2.service.XiVPNService {
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "SimpleVPNService";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String CHANNEL_ID = "VPN_CHANNEL";
    private static final int NOTIFICATION_ID = 1;
    private static final int SOCKS_PORT = 18964;
    @org.jetbrains.annotations.NotNull
    private com.myvpn.simple.SimpleVPNService.Status currentStatus = com.myvpn.simple.SimpleVPNService.Status.DISCONNECTED;
    @org.jetbrains.annotations.Nullable
    private android.os.ParcelFileDescriptor vpnInterface;
    @org.jetbrains.annotations.Nullable
    private com.myvpn.simple.TrojanConfig currentConfig;
    @org.jetbrains.annotations.NotNull
    private final com.myvpn.simple.SimpleVPNService.VPNBinder binder = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.Set<com.myvpn.simple.SimpleVPNService.VPNStatusListener> listeners = null;
    @org.jetbrains.annotations.Nullable
    private com.myvpn.simple.ui.AppExclusionManager appExclusionManager;
    @org.jetbrains.annotations.NotNull
    public static final com.myvpn.simple.SimpleVPNService.Companion Companion = null;
    
    public SimpleVPNService() {
        super();
    }
    
    @java.lang.Override
    public void onCreate() {
    }
    
    @java.lang.Override
    public int onStartCommand(@org.jetbrains.annotations.Nullable
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public android.os.IBinder onBind(@org.jetbrains.annotations.NotNull
    android.content.Intent intent) {
        return null;
    }
    
    private final void createNotificationChannel() {
    }
    
    @kotlin.jvm.Synchronized
    private final synchronized void startVPN(com.myvpn.simple.TrojanConfig config) {
    }
    
    private final boolean establishVPN() {
        return false;
    }
    
    private final void configureAppExclusion(android.net.VpnService.Builder builder) {
    }
    
    private final com.myvpn.simple.xray.XrayConfig buildXrayConfig(com.myvpn.simple.TrojanConfig config) {
        return null;
    }
    
    @kotlin.jvm.Synchronized
    private final synchronized void stopVPN() {
    }
    
    private final void updateStatus(com.myvpn.simple.SimpleVPNService.Status status) {
    }
    
    private final void notifyMessage(java.lang.String message) {
    }
    
    private final android.app.Notification createNotification(java.lang.String text) {
        return null;
    }
    
    @java.lang.Override
    public void onRevoke() {
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/myvpn/simple/SimpleVPNService$Companion;", "", "()V", "CHANNEL_ID", "", "NOTIFICATION_ID", "", "SOCKS_PORT", "TAG", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/myvpn/simple/SimpleVPNService$Status;", "", "(Ljava/lang/String;I)V", "DISCONNECTED", "CONNECTING", "CONNECTED", "app_debug"})
    public static enum Status {
        /*public static final*/ DISCONNECTED /* = new DISCONNECTED() */,
        /*public static final*/ CONNECTING /* = new CONNECTING() */,
        /*public static final*/ CONNECTED /* = new CONNECTED() */;
        
        Status() {
        }
        
        @org.jetbrains.annotations.NotNull
        public static kotlin.enums.EnumEntries<com.myvpn.simple.SimpleVPNService.Status> getEntries() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0014J\u000e\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b8F\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0019"}, d2 = {"Lcom/myvpn/simple/SimpleVPNService$VPNBinder;", "Landroid/os/Binder;", "(Lcom/myvpn/simple/SimpleVPNService;)V", "appExclusionManager", "Lcom/myvpn/simple/ui/AppExclusionManager;", "getAppExclusionManager", "()Lcom/myvpn/simple/ui/AppExclusionManager;", "service", "Lcom/myvpn/simple/SimpleVPNService;", "getService", "()Lcom/myvpn/simple/SimpleVPNService;", "status", "Lcom/myvpn/simple/SimpleVPNService$Status;", "getStatus", "()Lcom/myvpn/simple/SimpleVPNService$Status;", "addListener", "", "listener", "Lcom/myvpn/simple/SimpleVPNService$VPNStatusListener;", "connect", "", "config", "Lcom/myvpn/simple/TrojanConfig;", "disconnect", "removeListener", "app_debug"})
    public final class VPNBinder extends android.os.Binder {
        
        public VPNBinder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.myvpn.simple.SimpleVPNService getService() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.myvpn.simple.SimpleVPNService.Status getStatus() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final com.myvpn.simple.ui.AppExclusionManager getAppExclusionManager() {
            return null;
        }
        
        public final boolean addListener(@org.jetbrains.annotations.NotNull
        com.myvpn.simple.SimpleVPNService.VPNStatusListener listener) {
            return false;
        }
        
        public final boolean removeListener(@org.jetbrains.annotations.NotNull
        com.myvpn.simple.SimpleVPNService.VPNStatusListener listener) {
            return false;
        }
        
        public final void connect(@org.jetbrains.annotations.NotNull
        com.myvpn.simple.TrojanConfig config) {
        }
        
        public final void disconnect() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\t"}, d2 = {"Lcom/myvpn/simple/SimpleVPNService$VPNStatusListener;", "", "onMessage", "", "message", "", "onStatusChanged", "status", "Lcom/myvpn/simple/SimpleVPNService$Status;", "app_debug"})
    public static abstract interface VPNStatusListener {
        
        public abstract void onStatusChanged(@org.jetbrains.annotations.NotNull
        com.myvpn.simple.SimpleVPNService.Status status);
        
        public abstract void onMessage(@org.jetbrains.annotations.NotNull
        java.lang.String message);
    }
}