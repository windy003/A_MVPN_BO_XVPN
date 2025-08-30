package com.myvpn.simple;

import android.util.Base64;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionParser {
    
    public static List<TrojanConfig> parseSubscription(String subscriptionContent) {
        List<TrojanConfig> configs = new ArrayList<>();
        
        try {
            String decoded = new String(Base64.decode(subscriptionContent, Base64.DEFAULT));
            String[] lines = decoded.split("\n");
            
            for (String line : lines) {
                line = line.trim();
                if (line.startsWith("trojan://")) {
                    TrojanConfig config = parseTrojanUrl(line);
                    if (config != null && config.isValid()) {
                        configs.add(config);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return configs;
    }
    
    public static TrojanConfig parseTrojanUrl(String url) {
        try {
            URI uri = URI.create(url);
            String userInfo = uri.getUserInfo();
            String host = uri.getHost();
            int port = uri.getPort();
            
            if (userInfo != null && host != null && port > 0) {
                TrojanConfig config = new TrojanConfig();
                config.password = userInfo;
                config.server = host;
                config.port = port;
                
                String query = uri.getQuery();
                if (query != null) {
                    String[] params = query.split("&");
                    for (String param : params) {
                        String[] kv = param.split("=");
                        if (kv.length == 2) {
                            if ("sni".equals(kv[0])) {
                                config.sni = kv[1];
                            } else if ("allowInsecure".equals(kv[0])) {
                                config.allowInsecure = "1".equals(kv[1]);
                            }
                        }
                    }
                }
                
                return config;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
}