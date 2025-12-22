package com.myvpn.simple.xray;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001:\b&\'()*+,-B?\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0011\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\bH\u00c6\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\nH\u00c6\u0003JC\u0010\u001e\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u00c6\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020#H\u00d6\u0001J\t\u0010$\u001a\u00020%H\u00d6\u0001R\"\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019\u00a8\u0006."}, d2 = {"Lcom/myvpn/simple/xray/XrayConfig;", "", "inbounds", "", "Lcom/myvpn/simple/xray/XrayConfig$Inbound;", "outbounds", "Lcom/myvpn/simple/xray/XrayConfig$Outbound;", "log", "Lcom/myvpn/simple/xray/XrayConfig$Log;", "routing", "Lcom/myvpn/simple/xray/XrayConfig$Routing;", "(Ljava/util/List;Ljava/util/List;Lcom/myvpn/simple/xray/XrayConfig$Log;Lcom/myvpn/simple/xray/XrayConfig$Routing;)V", "getInbounds", "()Ljava/util/List;", "setInbounds", "(Ljava/util/List;)V", "getLog", "()Lcom/myvpn/simple/xray/XrayConfig$Log;", "setLog", "(Lcom/myvpn/simple/xray/XrayConfig$Log;)V", "getOutbounds", "setOutbounds", "getRouting", "()Lcom/myvpn/simple/xray/XrayConfig$Routing;", "setRouting", "(Lcom/myvpn/simple/xray/XrayConfig$Routing;)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Inbound", "Log", "Outbound", "Routing", "RoutingRule", "Sniffing", "StreamSettings", "TlsSettings", "app_debug"})
public final class XrayConfig {
    @org.jetbrains.annotations.Nullable
    private java.util.List<com.myvpn.simple.xray.XrayConfig.Inbound> inbounds;
    @org.jetbrains.annotations.Nullable
    private java.util.List<com.myvpn.simple.xray.XrayConfig.Outbound> outbounds;
    @org.jetbrains.annotations.NotNull
    private com.myvpn.simple.xray.XrayConfig.Log log;
    @org.jetbrains.annotations.Nullable
    private com.myvpn.simple.xray.XrayConfig.Routing routing;
    
    public XrayConfig(@org.jetbrains.annotations.Nullable
    java.util.List<com.myvpn.simple.xray.XrayConfig.Inbound> inbounds, @org.jetbrains.annotations.Nullable
    java.util.List<com.myvpn.simple.xray.XrayConfig.Outbound> outbounds, @org.jetbrains.annotations.NotNull
    com.myvpn.simple.xray.XrayConfig.Log log, @org.jetbrains.annotations.Nullable
    com.myvpn.simple.xray.XrayConfig.Routing routing) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.myvpn.simple.xray.XrayConfig.Inbound> getInbounds() {
        return null;
    }
    
    public final void setInbounds(@org.jetbrains.annotations.Nullable
    java.util.List<com.myvpn.simple.xray.XrayConfig.Inbound> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.myvpn.simple.xray.XrayConfig.Outbound> getOutbounds() {
        return null;
    }
    
    public final void setOutbounds(@org.jetbrains.annotations.Nullable
    java.util.List<com.myvpn.simple.xray.XrayConfig.Outbound> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.myvpn.simple.xray.XrayConfig.Log getLog() {
        return null;
    }
    
    public final void setLog(@org.jetbrains.annotations.NotNull
    com.myvpn.simple.xray.XrayConfig.Log p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.myvpn.simple.xray.XrayConfig.Routing getRouting() {
        return null;
    }
    
    public final void setRouting(@org.jetbrains.annotations.Nullable
    com.myvpn.simple.xray.XrayConfig.Routing p0) {
    }
    
    public XrayConfig() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.myvpn.simple.xray.XrayConfig.Inbound> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.myvpn.simple.xray.XrayConfig.Outbound> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.myvpn.simple.xray.XrayConfig.Log component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.myvpn.simple.xray.XrayConfig.Routing component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.myvpn.simple.xray.XrayConfig copy(@org.jetbrains.annotations.Nullable
    java.util.List<com.myvpn.simple.xray.XrayConfig.Inbound> inbounds, @org.jetbrains.annotations.Nullable
    java.util.List<com.myvpn.simple.xray.XrayConfig.Outbound> outbounds, @org.jetbrains.annotations.NotNull
    com.myvpn.simple.xray.XrayConfig.Log log, @org.jetbrains.annotations.Nullable
    com.myvpn.simple.xray.XrayConfig.Routing routing) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\u0017\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\bH\u00c6\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\nH\u00c6\u0003JK\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u00c6\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\'\u001a\u00020\u0005H\u00d6\u0001J\t\u0010(\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR(\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d\u00a8\u0006)"}, d2 = {"Lcom/myvpn/simple/xray/XrayConfig$Inbound;", "", "protocol", "", "port", "", "listen", "settings", "", "sniffing", "Lcom/myvpn/simple/xray/XrayConfig$Sniffing;", "(Ljava/lang/String;ILjava/lang/String;Ljava/util/Map;Lcom/myvpn/simple/xray/XrayConfig$Sniffing;)V", "getListen", "()Ljava/lang/String;", "setListen", "(Ljava/lang/String;)V", "getPort", "()I", "setPort", "(I)V", "getProtocol", "setProtocol", "getSettings", "()Ljava/util/Map;", "setSettings", "(Ljava/util/Map;)V", "getSniffing", "()Lcom/myvpn/simple/xray/XrayConfig$Sniffing;", "setSniffing", "(Lcom/myvpn/simple/xray/XrayConfig$Sniffing;)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
    public static final class Inbound {
        @org.jetbrains.annotations.NotNull
        private java.lang.String protocol;
        private int port;
        @org.jetbrains.annotations.NotNull
        private java.lang.String listen;
        @org.jetbrains.annotations.Nullable
        private java.util.Map<java.lang.String, ? extends java.lang.Object> settings;
        @org.jetbrains.annotations.Nullable
        private com.myvpn.simple.xray.XrayConfig.Sniffing sniffing;
        
        public Inbound(@org.jetbrains.annotations.NotNull
        java.lang.String protocol, int port, @org.jetbrains.annotations.NotNull
        java.lang.String listen, @org.jetbrains.annotations.Nullable
        java.util.Map<java.lang.String, ? extends java.lang.Object> settings, @org.jetbrains.annotations.Nullable
        com.myvpn.simple.xray.XrayConfig.Sniffing sniffing) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getProtocol() {
            return null;
        }
        
        public final void setProtocol(@org.jetbrains.annotations.NotNull
        java.lang.String p0) {
        }
        
        public final int getPort() {
            return 0;
        }
        
        public final void setPort(int p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getListen() {
            return null;
        }
        
        public final void setListen(@org.jetbrains.annotations.NotNull
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.util.Map<java.lang.String, java.lang.Object> getSettings() {
            return null;
        }
        
        public final void setSettings(@org.jetbrains.annotations.Nullable
        java.util.Map<java.lang.String, ? extends java.lang.Object> p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final com.myvpn.simple.xray.XrayConfig.Sniffing getSniffing() {
            return null;
        }
        
        public final void setSniffing(@org.jetbrains.annotations.Nullable
        com.myvpn.simple.xray.XrayConfig.Sniffing p0) {
        }
        
        public Inbound() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component1() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.util.Map<java.lang.String, java.lang.Object> component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final com.myvpn.simple.xray.XrayConfig.Sniffing component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.myvpn.simple.xray.XrayConfig.Inbound copy(@org.jetbrains.annotations.NotNull
        java.lang.String protocol, int port, @org.jetbrains.annotations.NotNull
        java.lang.String listen, @org.jetbrains.annotations.Nullable
        java.util.Map<java.lang.String, ? extends java.lang.Object> settings, @org.jetbrains.annotations.Nullable
        com.myvpn.simple.xray.XrayConfig.Sniffing sniffing) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004\u00a8\u0006\u0010"}, d2 = {"Lcom/myvpn/simple/xray/XrayConfig$Log;", "", "loglevel", "", "(Ljava/lang/String;)V", "getLoglevel", "()Ljava/lang/String;", "setLoglevel", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
    public static final class Log {
        @org.jetbrains.annotations.NotNull
        private java.lang.String loglevel;
        
        public Log(@org.jetbrains.annotations.NotNull
        java.lang.String loglevel) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getLoglevel() {
            return null;
        }
        
        public final void setLoglevel(@org.jetbrains.annotations.NotNull
        java.lang.String p0) {
        }
        
        public Log() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.myvpn.simple.xray.XrayConfig.Log copy(@org.jetbrains.annotations.NotNull
        java.lang.String loglevel) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J5\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0001X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\n\"\u0004\b\u0016\u0010\f\u00a8\u0006\""}, d2 = {"Lcom/myvpn/simple/xray/XrayConfig$Outbound;", "", "tag", "", "protocol", "settings", "streamSettings", "Lcom/myvpn/simple/xray/XrayConfig$StreamSettings;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Lcom/myvpn/simple/xray/XrayConfig$StreamSettings;)V", "getProtocol", "()Ljava/lang/String;", "setProtocol", "(Ljava/lang/String;)V", "getSettings", "()Ljava/lang/Object;", "setSettings", "(Ljava/lang/Object;)V", "getStreamSettings", "()Lcom/myvpn/simple/xray/XrayConfig$StreamSettings;", "setStreamSettings", "(Lcom/myvpn/simple/xray/XrayConfig$StreamSettings;)V", "getTag", "setTag", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
    public static final class Outbound {
        @org.jetbrains.annotations.NotNull
        private java.lang.String tag;
        @org.jetbrains.annotations.NotNull
        private java.lang.String protocol;
        @org.jetbrains.annotations.Nullable
        private java.lang.Object settings;
        @org.jetbrains.annotations.Nullable
        private com.myvpn.simple.xray.XrayConfig.StreamSettings streamSettings;
        
        public Outbound(@org.jetbrains.annotations.NotNull
        java.lang.String tag, @org.jetbrains.annotations.NotNull
        java.lang.String protocol, @org.jetbrains.annotations.Nullable
        java.lang.Object settings, @org.jetbrains.annotations.Nullable
        com.myvpn.simple.xray.XrayConfig.StreamSettings streamSettings) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getTag() {
            return null;
        }
        
        public final void setTag(@org.jetbrains.annotations.NotNull
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getProtocol() {
            return null;
        }
        
        public final void setProtocol(@org.jetbrains.annotations.NotNull
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Object getSettings() {
            return null;
        }
        
        public final void setSettings(@org.jetbrains.annotations.Nullable
        java.lang.Object p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final com.myvpn.simple.xray.XrayConfig.StreamSettings getStreamSettings() {
            return null;
        }
        
        public final void setStreamSettings(@org.jetbrains.annotations.Nullable
        com.myvpn.simple.xray.XrayConfig.StreamSettings p0) {
        }
        
        public Outbound() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Object component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final com.myvpn.simple.xray.XrayConfig.StreamSettings component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.myvpn.simple.xray.XrayConfig.Outbound copy(@org.jetbrains.annotations.NotNull
        java.lang.String tag, @org.jetbrains.annotations.NotNull
        java.lang.String protocol, @org.jetbrains.annotations.Nullable
        java.lang.Object settings, @org.jetbrains.annotations.Nullable
        com.myvpn.simple.xray.XrayConfig.StreamSettings streamSettings) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0005J\u0011\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u00c6\u0003J\u001b\u0010\n\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\"\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005\u00a8\u0006\u0012"}, d2 = {"Lcom/myvpn/simple/xray/XrayConfig$Routing;", "", "rules", "", "Lcom/myvpn/simple/xray/XrayConfig$RoutingRule;", "(Ljava/util/List;)V", "getRules", "()Ljava/util/List;", "setRules", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
    public static final class Routing {
        @org.jetbrains.annotations.Nullable
        private java.util.List<com.myvpn.simple.xray.XrayConfig.RoutingRule> rules;
        
        public Routing(@org.jetbrains.annotations.Nullable
        java.util.List<com.myvpn.simple.xray.XrayConfig.RoutingRule> rules) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.util.List<com.myvpn.simple.xray.XrayConfig.RoutingRule> getRules() {
            return null;
        }
        
        public final void setRules(@org.jetbrains.annotations.Nullable
        java.util.List<com.myvpn.simple.xray.XrayConfig.RoutingRule> p0) {
        }
        
        public Routing() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.util.List<com.myvpn.simple.xray.XrayConfig.RoutingRule> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.myvpn.simple.xray.XrayConfig.Routing copy(@org.jetbrains.annotations.Nullable
        java.util.List<com.myvpn.simple.xray.XrayConfig.RoutingRule> rules) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n\u00a8\u0006\u0019"}, d2 = {"Lcom/myvpn/simple/xray/XrayConfig$RoutingRule;", "", "type", "", "outboundTag", "network", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getNetwork", "()Ljava/lang/String;", "setNetwork", "(Ljava/lang/String;)V", "getOutboundTag", "setOutboundTag", "getType", "setType", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
    public static final class RoutingRule {
        @org.jetbrains.annotations.NotNull
        private java.lang.String type;
        @org.jetbrains.annotations.NotNull
        private java.lang.String outboundTag;
        @org.jetbrains.annotations.NotNull
        private java.lang.String network;
        
        public RoutingRule(@org.jetbrains.annotations.NotNull
        java.lang.String type, @org.jetbrains.annotations.NotNull
        java.lang.String outboundTag, @org.jetbrains.annotations.NotNull
        java.lang.String network) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getType() {
            return null;
        }
        
        public final void setType(@org.jetbrains.annotations.NotNull
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getOutboundTag() {
            return null;
        }
        
        public final void setOutboundTag(@org.jetbrains.annotations.NotNull
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getNetwork() {
            return null;
        }
        
        public final void setNetwork(@org.jetbrains.annotations.NotNull
        java.lang.String p0) {
        }
        
        public RoutingRule() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.myvpn.simple.xray.XrayConfig.RoutingRule copy(@org.jetbrains.annotations.NotNull
        java.lang.String type, @org.jetbrains.annotations.NotNull
        java.lang.String outboundTag, @org.jetbrains.annotations.NotNull
        java.lang.String network) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\bJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\u0011\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J/\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0006H\u00d6\u0001R\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010\u00a8\u0006\u001c"}, d2 = {"Lcom/myvpn/simple/xray/XrayConfig$Sniffing;", "", "enabled", "", "destOverride", "", "", "routeOnly", "(ZLjava/util/List;Z)V", "getDestOverride", "()Ljava/util/List;", "setDestOverride", "(Ljava/util/List;)V", "getEnabled", "()Z", "setEnabled", "(Z)V", "getRouteOnly", "setRouteOnly", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
    public static final class Sniffing {
        private boolean enabled;
        @org.jetbrains.annotations.Nullable
        private java.util.List<java.lang.String> destOverride;
        private boolean routeOnly;
        
        public Sniffing(boolean enabled, @org.jetbrains.annotations.Nullable
        java.util.List<java.lang.String> destOverride, boolean routeOnly) {
            super();
        }
        
        public final boolean getEnabled() {
            return false;
        }
        
        public final void setEnabled(boolean p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.util.List<java.lang.String> getDestOverride() {
            return null;
        }
        
        public final void setDestOverride(@org.jetbrains.annotations.Nullable
        java.util.List<java.lang.String> p0) {
        }
        
        public final boolean getRouteOnly() {
            return false;
        }
        
        public final void setRouteOnly(boolean p0) {
        }
        
        public Sniffing() {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.util.List<java.lang.String> component2() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.myvpn.simple.xray.XrayConfig.Sniffing copy(boolean enabled, @org.jetbrains.annotations.Nullable
        java.util.List<java.lang.String> destOverride, boolean routeOnly) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J)\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001c"}, d2 = {"Lcom/myvpn/simple/xray/XrayConfig$StreamSettings;", "", "network", "", "security", "tlsSettings", "Lcom/myvpn/simple/xray/XrayConfig$TlsSettings;", "(Ljava/lang/String;Ljava/lang/String;Lcom/myvpn/simple/xray/XrayConfig$TlsSettings;)V", "getNetwork", "()Ljava/lang/String;", "setNetwork", "(Ljava/lang/String;)V", "getSecurity", "setSecurity", "getTlsSettings", "()Lcom/myvpn/simple/xray/XrayConfig$TlsSettings;", "setTlsSettings", "(Lcom/myvpn/simple/xray/XrayConfig$TlsSettings;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
    public static final class StreamSettings {
        @org.jetbrains.annotations.NotNull
        private java.lang.String network;
        @org.jetbrains.annotations.NotNull
        private java.lang.String security;
        @org.jetbrains.annotations.Nullable
        private com.myvpn.simple.xray.XrayConfig.TlsSettings tlsSettings;
        
        public StreamSettings(@org.jetbrains.annotations.NotNull
        java.lang.String network, @org.jetbrains.annotations.NotNull
        java.lang.String security, @org.jetbrains.annotations.Nullable
        com.myvpn.simple.xray.XrayConfig.TlsSettings tlsSettings) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getNetwork() {
            return null;
        }
        
        public final void setNetwork(@org.jetbrains.annotations.NotNull
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getSecurity() {
            return null;
        }
        
        public final void setSecurity(@org.jetbrains.annotations.NotNull
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final com.myvpn.simple.xray.XrayConfig.TlsSettings getTlsSettings() {
            return null;
        }
        
        public final void setTlsSettings(@org.jetbrains.annotations.Nullable
        com.myvpn.simple.xray.XrayConfig.TlsSettings p0) {
        }
        
        public StreamSettings() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final com.myvpn.simple.xray.XrayConfig.TlsSettings component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.myvpn.simple.xray.XrayConfig.StreamSettings copy(@org.jetbrains.annotations.NotNull
        java.lang.String network, @org.jetbrains.annotations.NotNull
        java.lang.String security, @org.jetbrains.annotations.Nullable
        com.myvpn.simple.xray.XrayConfig.TlsSettings tlsSettings) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u0017"}, d2 = {"Lcom/myvpn/simple/xray/XrayConfig$TlsSettings;", "", "serverName", "", "allowInsecure", "", "(Ljava/lang/String;Z)V", "getAllowInsecure", "()Z", "setAllowInsecure", "(Z)V", "getServerName", "()Ljava/lang/String;", "setServerName", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
    public static final class TlsSettings {
        @org.jetbrains.annotations.NotNull
        private java.lang.String serverName;
        private boolean allowInsecure;
        
        public TlsSettings(@org.jetbrains.annotations.NotNull
        java.lang.String serverName, boolean allowInsecure) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getServerName() {
            return null;
        }
        
        public final void setServerName(@org.jetbrains.annotations.NotNull
        java.lang.String p0) {
        }
        
        public final boolean getAllowInsecure() {
            return false;
        }
        
        public final void setAllowInsecure(boolean p0) {
        }
        
        public TlsSettings() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component1() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.myvpn.simple.xray.XrayConfig.TlsSettings copy(@org.jetbrains.annotations.NotNull
        java.lang.String serverName, boolean allowInsecure) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String toString() {
            return null;
        }
    }
}