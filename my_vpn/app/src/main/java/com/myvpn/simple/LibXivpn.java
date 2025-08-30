package com.myvpn.simple;

public class LibXivpn {
    
    static {
        System.loadLibrary("xivpn");
    }
    
    public static native String xivpn_version();
    
    public static native String xivpn_start(String config, int socksPort, int fd, String logFile, String asset, SocketProtect protect);
    
    public static native void xivpn_stop();
    
    public static native void xivpn_init();
}