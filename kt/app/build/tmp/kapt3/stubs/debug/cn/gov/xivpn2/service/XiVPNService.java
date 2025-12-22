package cn.gov.xivpn2.service;

/**
 * 占位服务类，用于满足 libxivpn.so 初始化时的类依赖要求
 * 实际的 VPN 功能由 com.myvpn.simple.SimpleVPNService 实现
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0016\u0018\u0000 \b2\u00020\u00012\u00020\u0002:\u0001\bB\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016\u00a8\u0006\t"}, d2 = {"Lcn/gov/xivpn2/service/XiVPNService;", "Landroid/net/VpnService;", "Lcn/gov/xivpn2/service/SocketProtect;", "()V", "protectFd", "", "fd", "", "Companion", "app_debug"})
public class XiVPNService extends android.net.VpnService implements cn.gov.xivpn2.service.SocketProtect {
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "XiVPNService";
    @org.jetbrains.annotations.NotNull
    public static final cn.gov.xivpn2.service.XiVPNService.Companion Companion = null;
    
    public XiVPNService() {
        super();
    }
    
    @java.lang.Override
    public void protectFd(int fd) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcn/gov/xivpn2/service/XiVPNService$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}