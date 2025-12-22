package com.myvpn.simple.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriptions")
data class Subscription(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    var name: String = "",           // 订阅名称
    var url: String = "",            // 订阅URL
    var updateTime: Long = 0,        // 最后更新时间
    var nodeCount: Int = 0,          // 节点数量
    var createTime: Long = 0
) {
    init {
        if (createTime == 0L) {
            createTime = System.currentTimeMillis()
        }
    }
}
