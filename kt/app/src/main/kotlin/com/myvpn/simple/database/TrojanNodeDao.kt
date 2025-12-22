package com.myvpn.simple.database

import androidx.room.*

@Dao
interface TrojanNodeDao {

    @Query("SELECT * FROM trojan_nodes ORDER BY createTime DESC")
    fun getAllNodes(): List<TrojanNode>

    @Query("SELECT * FROM trojan_nodes WHERE isSelected = 1 LIMIT 1")
    fun getSelectedNode(): TrojanNode?

    @Query("SELECT * FROM trojan_nodes WHERE id = :id")
    fun getNodeById(id: Long): TrojanNode?

    @Insert
    fun insertNode(node: TrojanNode): Long

    @Update
    fun updateNode(node: TrojanNode)

    @Delete
    fun deleteNode(node: TrojanNode)

    @Query("UPDATE trojan_nodes SET isSelected = 0")
    fun clearAllSelections()

    @Query("UPDATE trojan_nodes SET isSelected = 1 WHERE id = :nodeId")
    fun selectNode(nodeId: Long)

    @Query("DELETE FROM trojan_nodes WHERE id = :nodeId")
    fun deleteNodeById(nodeId: Long)

    @Query("SELECT COUNT(*) FROM trojan_nodes")
    fun getNodeCount(): Int

    @Query("SELECT * FROM trojan_nodes WHERE subscriptionId = :subscriptionId ORDER BY createTime DESC")
    fun getNodesBySubscription(subscriptionId: Long): List<TrojanNode>

    @Query("DELETE FROM trojan_nodes WHERE subscriptionId = :subscriptionId")
    fun deleteNodesBySubscription(subscriptionId: Long)

    @Query("SELECT COUNT(*) FROM trojan_nodes WHERE subscriptionId = :subscriptionId")
    fun getNodeCountBySubscription(subscriptionId: Long): Int

    @Query("DELETE FROM trojan_nodes")
    fun deleteAllNodes()
}
