package com.myvpn.simple.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "trojan_nodes")
public class TrojanNode {
    @PrimaryKey(autoGenerate = true)
    public long id;
    
    public String name;
    public String server;
    public int port;
    public String password;
    public String sni;
    public boolean allowInsecure;
    public long createTime;
    public boolean isSelected;
    
    public TrojanNode() {
        this.createTime = System.currentTimeMillis();
        this.isSelected = false;
    }
    
    @Ignore
    public TrojanNode(String name, String server, int port, String password) {
        this();
        this.name = name;
        this.server = server;
        this.port = port;
        this.password = password;
    }
    
    public static TrojanNode fromConfig(com.myvpn.simple.TrojanConfig config) {
        TrojanNode node = new TrojanNode();
        node.name = config.server + ":" + config.port;
        node.server = config.server;
        node.port = config.port;
        node.password = config.password;
        node.sni = config.sni;
        node.allowInsecure = config.allowInsecure;
        return node;
    }
    
    public com.myvpn.simple.TrojanConfig toConfig() {
        com.myvpn.simple.TrojanConfig config = new com.myvpn.simple.TrojanConfig();
        config.server = this.server;
        config.port = this.port;
        config.password = this.password;
        config.sni = this.sni;
        config.allowInsecure = this.allowInsecure;
        return config;
    }
    
    public String getDisplayName() {
        return name != null && !name.isEmpty() ? name : (server + ":" + port);
    }
}