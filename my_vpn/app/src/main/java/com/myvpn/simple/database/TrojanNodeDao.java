package com.myvpn.simple.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface TrojanNodeDao {
    
    @Query("SELECT * FROM trojan_nodes ORDER BY createTime DESC")
    List<TrojanNode> getAllNodes();
    
    @Query("SELECT * FROM trojan_nodes WHERE isSelected = 1 LIMIT 1")
    TrojanNode getSelectedNode();
    
    @Query("SELECT * FROM trojan_nodes WHERE id = :id")
    TrojanNode getNodeById(long id);
    
    @Insert
    long insertNode(TrojanNode node);
    
    @Update
    void updateNode(TrojanNode node);
    
    @Delete
    void deleteNode(TrojanNode node);
    
    @Query("UPDATE trojan_nodes SET isSelected = 0")
    void clearAllSelections();
    
    @Query("UPDATE trojan_nodes SET isSelected = 1 WHERE id = :nodeId")
    void selectNode(long nodeId);
    
    @Query("DELETE FROM trojan_nodes WHERE id = :nodeId")
    void deleteNodeById(long nodeId);
    
    @Query("SELECT COUNT(*) FROM trojan_nodes")
    int getNodeCount();
}