package com.myvpn.simple;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0011\u001a\u00020\nH\u0002J\b\u0010\u0012\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/myvpn/simple/VPNTileService;", "Landroid/service/quicksettings/TileService;", "()V", "isServiceBound", "", "serviceConnection", "Landroid/content/ServiceConnection;", "vpnService", "Lcom/myvpn/simple/SimpleVPNService;", "connectVPN", "", "disconnectVPN", "onClick", "onCreate", "onDestroy", "onStartListening", "onStopListening", "resetToDisconnected", "updateTile", "Companion", "app_debug"})
@androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.N)
public final class VPNTileService extends android.service.quicksettings.TileService {
    @org.jetbrains.annotations.Nullable
    private com.myvpn.simple.SimpleVPNService vpnService;
    private boolean isServiceBound = false;
    @org.jetbrains.annotations.NotNull
    private final android.content.ServiceConnection serviceConnection = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "VPNTileService";
    @org.jetbrains.annotations.NotNull
    public static final com.myvpn.simple.VPNTileService.Companion Companion = null;
    
    public VPNTileService() {
        super();
    }
    
    @java.lang.Override
    public void onCreate() {
    }
    
    @java.lang.Override
    public void onStartListening() {
    }
    
    @java.lang.Override
    public void onStopListening() {
    }
    
    @java.lang.Override
    public void onClick() {
    }
    
    private final void connectVPN() {
    }
    
    private final void resetToDisconnected() {
    }
    
    private final void disconnectVPN() {
    }
    
    private final void updateTile() {
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/myvpn/simple/VPNTileService$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}