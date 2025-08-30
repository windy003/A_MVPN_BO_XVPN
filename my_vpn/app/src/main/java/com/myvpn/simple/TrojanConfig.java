package com.myvpn.simple;

import java.io.Serializable;

public class TrojanConfig implements Serializable {
    public String server;
    public int port;
    public String password;
    public String sni;
    public boolean allowInsecure = false;
    
    public TrojanConfig() {}
    
    public TrojanConfig(String server, int port, String password) {
        this.server = server;
        this.port = port;
        this.password = password;
    }
    
    public boolean isValid() {
        return server != null && !server.isEmpty() && 
               port > 0 && port <= 65535 && 
               password != null && !password.isEmpty();
    }
    
    @Override
    public String toString() {
        return "trojan://" + password + "@" + server + ":" + port;
    }
}