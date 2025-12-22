package com.myvpn.simple.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\b\u0010\u0004\u001a\u00020\u0003H\'J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\'J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\nH\'J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eH\'J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0010\u001a\u00020\nH\'J\b\u0010\u0011\u001a\u00020\u0012H\'J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\nH\'J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u0006\u0010\f\u001a\u00020\nH\'J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0007H\'J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\'J\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\'\u00a8\u0006\u0019"}, d2 = {"Lcom/myvpn/simple/database/TrojanNodeDao;", "", "clearAllSelections", "", "deleteAllNodes", "deleteNode", "node", "Lcom/myvpn/simple/database/TrojanNode;", "deleteNodeById", "nodeId", "", "deleteNodesBySubscription", "subscriptionId", "getAllNodes", "", "getNodeById", "id", "getNodeCount", "", "getNodeCountBySubscription", "getNodesBySubscription", "getSelectedNode", "insertNode", "selectNode", "updateNode", "app_debug"})
@androidx.room.Dao
public abstract interface TrojanNodeDao {
    
    @androidx.room.Query(value = "SELECT * FROM trojan_nodes ORDER BY createTime DESC")
    @org.jetbrains.annotations.NotNull
    public abstract java.util.List<com.myvpn.simple.database.TrojanNode> getAllNodes();
    
    @androidx.room.Query(value = "SELECT * FROM trojan_nodes WHERE isSelected = 1 LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract com.myvpn.simple.database.TrojanNode getSelectedNode();
    
    @androidx.room.Query(value = "SELECT * FROM trojan_nodes WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract com.myvpn.simple.database.TrojanNode getNodeById(long id);
    
    @androidx.room.Insert
    public abstract long insertNode(@org.jetbrains.annotations.NotNull
    com.myvpn.simple.database.TrojanNode node);
    
    @androidx.room.Update
    public abstract void updateNode(@org.jetbrains.annotations.NotNull
    com.myvpn.simple.database.TrojanNode node);
    
    @androidx.room.Delete
    public abstract void deleteNode(@org.jetbrains.annotations.NotNull
    com.myvpn.simple.database.TrojanNode node);
    
    @androidx.room.Query(value = "UPDATE trojan_nodes SET isSelected = 0")
    public abstract void clearAllSelections();
    
    @androidx.room.Query(value = "UPDATE trojan_nodes SET isSelected = 1 WHERE id = :nodeId")
    public abstract void selectNode(long nodeId);
    
    @androidx.room.Query(value = "DELETE FROM trojan_nodes WHERE id = :nodeId")
    public abstract void deleteNodeById(long nodeId);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM trojan_nodes")
    public abstract int getNodeCount();
    
    @androidx.room.Query(value = "SELECT * FROM trojan_nodes WHERE subscriptionId = :subscriptionId ORDER BY createTime DESC")
    @org.jetbrains.annotations.NotNull
    public abstract java.util.List<com.myvpn.simple.database.TrojanNode> getNodesBySubscription(long subscriptionId);
    
    @androidx.room.Query(value = "DELETE FROM trojan_nodes WHERE subscriptionId = :subscriptionId")
    public abstract void deleteNodesBySubscription(long subscriptionId);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM trojan_nodes WHERE subscriptionId = :subscriptionId")
    public abstract int getNodeCountBySubscription(long subscriptionId);
    
    @androidx.room.Query(value = "DELETE FROM trojan_nodes")
    public abstract void deleteAllNodes();
}