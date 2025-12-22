package com.myvpn.simple.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.myvpn.simple.TrojanConfig

@Entity(tableName = "trojan_nodes")
class TrojanNode {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var name: String = ""
    var server: String = ""
    var port: Int = 0
    var password: String = ""
    var sni: String = ""
    var allowInsecure: Boolean = false
    var country: String = ""
    var latency: Int = -1  // 延迟(ms)，-1 表示未测试
    var subscriptionId: Long = 0  // 所属订阅ID，0表示手动添加
    var createTime: Long = 0
    var isSelected: Boolean = false

    constructor() {
        this.createTime = System.currentTimeMillis()
        this.isSelected = false
    }

    @Ignore
    constructor(name: String, server: String, port: Int, password: String) : this() {
        this.name = name
        this.server = server
        this.port = port
        this.password = password
    }

    companion object {
        fun fromConfig(config: TrojanConfig): TrojanNode {
            return TrojanNode().apply {
                name = if (config.remark.isNotEmpty()) config.remark else "${config.server}:${config.port}"
                server = config.server
                port = config.port
                password = config.password
                sni = config.sni
                allowInsecure = config.allowInsecure
                country = config.country
            }
        }
    }

    fun toConfig(): TrojanConfig {
        return TrojanConfig().apply {
            server = this@TrojanNode.server
            port = this@TrojanNode.port
            password = this@TrojanNode.password
            sni = this@TrojanNode.sni
            allowInsecure = this@TrojanNode.allowInsecure
        }
    }

    // Kotlin 风格的计算属性
    val displayName: String
        get() {
            val baseName = if (name.isNotEmpty()) name else "$server:$port"
            return if (country.isNotEmpty()) {
                "$country $baseName"
            } else {
                baseName
            }
        }
}
