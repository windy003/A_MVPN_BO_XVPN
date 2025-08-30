package com.myvpn.simple.xray;

import java.util.ArrayList;
import java.util.List;

public class TrojanSettings {
    public List<TrojanServer> servers = new ArrayList<>();
    
    public static class TrojanServer {
        public String address;
        public int port;
        public String password;
        
        public TrojanServer(String address, int port, String password) {
            this.address = address;
            this.port = port;
            this.password = password;
        }
    }
}