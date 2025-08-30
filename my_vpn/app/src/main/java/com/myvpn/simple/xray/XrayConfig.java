package com.myvpn.simple.xray;

import java.util.List;
import java.util.Map;

public class XrayConfig {
    public List<Inbound> inbounds;
    public List<Outbound> outbounds;
    public Log log = new Log();
    public Routing routing;
    
    public static class Log {
        public String loglevel = "warning";
    }
    
    public static class Inbound {
        public String protocol;
        public int port;
        public String listen;
        public Map<String, Object> settings;
        public Sniffing sniffing;
    }
    
    public static class Outbound {
        public String tag;
        public String protocol;
        public Object settings;
        public StreamSettings streamSettings;
    }
    
    public static class Sniffing {
        public boolean enabled;
        public List<String> destOverride;
        public boolean routeOnly;
    }
    
    public static class StreamSettings {
        public String network;
        public String security;
        public TlsSettings tlsSettings;
    }
    
    public static class TlsSettings {
        public String serverName;
        public boolean allowInsecure;
    }
    
    public static class Routing {
        public List<RoutingRule> rules;
    }
    
    public static class RoutingRule {
        public String type;
        public String outboundTag;
        public String network;
    }
}