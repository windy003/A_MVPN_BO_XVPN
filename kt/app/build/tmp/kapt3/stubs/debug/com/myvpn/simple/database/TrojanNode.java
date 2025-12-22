package com.myvpn.simple.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 72\u00020\u0001:\u00017B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0002B\'\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\tJ\u0006\u00105\u001a\u000206R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u0012R\u001e\u0010\u001d\u001a\u00020\u00168\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0018\"\u0004\b\u001f\u0010\u001aR\u001a\u0010 \u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\r\"\u0004\b!\u0010\u000fR\u001a\u0010\"\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010\u0012\"\u0004\b(\u0010\u0014R\u001a\u0010\b\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0012\"\u0004\b*\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010$\"\u0004\b,\u0010&R\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0012\"\u0004\b.\u0010\u0014R\u001a\u0010/\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u0012\"\u0004\b1\u0010\u0014R\u001a\u00102\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0018\"\u0004\b4\u0010\u001a\u00a8\u00068"}, d2 = {"Lcom/myvpn/simple/database/TrojanNode;", "", "()V", "name", "", "server", "port", "", "password", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "allowInsecure", "", "getAllowInsecure", "()Z", "setAllowInsecure", "(Z)V", "country", "getCountry", "()Ljava/lang/String;", "setCountry", "(Ljava/lang/String;)V", "createTime", "", "getCreateTime", "()J", "setCreateTime", "(J)V", "displayName", "getDisplayName", "id", "getId", "setId", "isSelected", "setSelected", "latency", "getLatency", "()I", "setLatency", "(I)V", "getName", "setName", "getPassword", "setPassword", "getPort", "setPort", "getServer", "setServer", "sni", "getSni", "setSni", "subscriptionId", "getSubscriptionId", "setSubscriptionId", "toConfig", "Lcom/myvpn/simple/TrojanConfig;", "Companion", "app_debug"})
@androidx.room.Entity(tableName = "trojan_nodes")
public final class TrojanNode {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private long id = 0L;
    @org.jetbrains.annotations.NotNull
    private java.lang.String name = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String server = "";
    private int port = 0;
    @org.jetbrains.annotations.NotNull
    private java.lang.String password = "";
    @org.jetbrains.annotations.NotNull
    private java.lang.String sni = "";
    private boolean allowInsecure = false;
    @org.jetbrains.annotations.NotNull
    private java.lang.String country = "";
    private int latency = -1;
    private long subscriptionId = 0L;
    private long createTime = 0L;
    private boolean isSelected = false;
    @org.jetbrains.annotations.NotNull
    public static final com.myvpn.simple.database.TrojanNode.Companion Companion = null;
    
    public final long getId() {
        return 0L;
    }
    
    public final void setId(long p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    public final void setName(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getServer() {
        return null;
    }
    
    public final void setServer(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    public final int getPort() {
        return 0;
    }
    
    public final void setPort(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPassword() {
        return null;
    }
    
    public final void setPassword(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSni() {
        return null;
    }
    
    public final void setSni(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    public final boolean getAllowInsecure() {
        return false;
    }
    
    public final void setAllowInsecure(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCountry() {
        return null;
    }
    
    public final void setCountry(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    public final int getLatency() {
        return 0;
    }
    
    public final void setLatency(int p0) {
    }
    
    public final long getSubscriptionId() {
        return 0L;
    }
    
    public final void setSubscriptionId(long p0) {
    }
    
    public final long getCreateTime() {
        return 0L;
    }
    
    public final void setCreateTime(long p0) {
    }
    
    public final boolean isSelected() {
        return false;
    }
    
    public final void setSelected(boolean p0) {
    }
    
    public TrojanNode() {
        super();
    }
    
    @androidx.room.Ignore
    public TrojanNode(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String server, int port, @org.jetbrains.annotations.NotNull
    java.lang.String password) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.myvpn.simple.TrojanConfig toConfig() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDisplayName() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/myvpn/simple/database/TrojanNode$Companion;", "", "()V", "fromConfig", "Lcom/myvpn/simple/database/TrojanNode;", "config", "Lcom/myvpn/simple/TrojanConfig;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.myvpn.simple.database.TrojanNode fromConfig(@org.jetbrains.annotations.NotNull
        com.myvpn.simple.TrojanConfig config) {
            return null;
        }
    }
}