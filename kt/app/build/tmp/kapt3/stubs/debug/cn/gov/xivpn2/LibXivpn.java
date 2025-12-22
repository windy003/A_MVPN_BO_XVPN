package cn.gov.xivpn2;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u0087 J9\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eH\u0087 J\t\u0010\u000f\u001a\u00020\u0004H\u0087 J\t\u0010\u0010\u001a\u00020\u0006H\u0087 \u00a8\u0006\u0011"}, d2 = {"Lcn/gov/xivpn2/LibXivpn;", "", "()V", "xivpn_init", "", "xivpn_start", "", "config", "socksPort", "", "fd", "logFile", "asset", "protect", "Lcn/gov/xivpn2/service/SocketProtect;", "xivpn_stop", "xivpn_version", "app_debug"})
public final class LibXivpn {
    @org.jetbrains.annotations.NotNull
    public static final cn.gov.xivpn2.LibXivpn INSTANCE = null;
    
    private LibXivpn() {
        super();
    }
    
    @kotlin.jvm.JvmStatic
    @org.jetbrains.annotations.NotNull
    public static final native java.lang.String xivpn_version() {
        return null;
    }
    
    @kotlin.jvm.JvmStatic
    @org.jetbrains.annotations.NotNull
    public static final native java.lang.String xivpn_start(@org.jetbrains.annotations.NotNull
    java.lang.String config, int socksPort, int fd, @org.jetbrains.annotations.NotNull
    java.lang.String logFile, @org.jetbrains.annotations.NotNull
    java.lang.String asset, @org.jetbrains.annotations.NotNull
    cn.gov.xivpn2.service.SocketProtect protect) {
        return null;
    }
    
    @kotlin.jvm.JvmStatic
    public static final native void xivpn_stop() {
    }
    
    @kotlin.jvm.JvmStatic
    public static final native void xivpn_init() {
    }
}