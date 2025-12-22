package com.myvpn.simple

import android.util.Base64
import java.net.URI
import java.net.URLDecoder

object SubscriptionParser {

    // 信息节点关键词（用于过滤非代理节点）
    private val INFO_NODE_KEYWORDS = listOf(
        "到期", "过期", "expire", "剩余", "流量",
        "订阅", "subscribe", "获取时间", "更新时间"
    )

    fun parseSubscription(subscriptionContent: String): List<TrojanConfig> {
        val configs = mutableListOf<TrojanConfig>()

        try {
            val decoded = String(Base64.decode(subscriptionContent, Base64.DEFAULT))
            val lines = decoded.split("\n")

            for (line in lines) {
                val trimmedLine = line.trim()
                if (trimmedLine.startsWith("trojan://")) {
                    val config = parseTrojanUrl(trimmedLine)
                    if (config != null && config.isValid()) {
                        // 过滤信息节点
                        if (!isInfoNode(config)) {
                            configs.add(config)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return configs
    }

    /**
     * 判断是否为信息节点（非真实代理节点）
     */
    private fun isInfoNode(config: TrojanConfig): Boolean {
        val remark = config.remark.lowercase()
        return INFO_NODE_KEYWORDS.any { keyword ->
            remark.contains(keyword.lowercase())
        }
    }

    /**
     * 从节点备注中提取区域信息
     * 支持多种格式：
     * - "香港 HK01" -> "香港"
     * - "美国-01" -> "美国"
     * - "HK 01" -> "HK"
     * - "日本|Tokyo" -> "日本"
     */
    private fun extractCountry(remark: String): String {
        if (remark.isEmpty()) return ""

        // 定义常见的分隔符
        val separators = listOf(" ", "-", "_", "|", "·", "•")

        // 尝试用分隔符分割
        for (separator in separators) {
            if (remark.contains(separator)) {
                val parts = remark.split(separator)
                if (parts.isNotEmpty()) {
                    val firstPart = parts[0].trim()
                    if (firstPart.isNotEmpty()) {
                        return firstPart
                    }
                }
            }
        }

        // 如果没有分隔符，尝试提取开头的中文字符或字母
        val chineseMatch = Regex("^[\u4e00-\u9fa5]+").find(remark)
        if (chineseMatch != null) {
            return chineseMatch.value
        }

        val letterMatch = Regex("^[A-Za-z]+").find(remark)
        if (letterMatch != null) {
            return letterMatch.value
        }

        // 如果都没有，返回整个 remark（最多取前10个字符）
        return if (remark.length > 10) remark.substring(0, 10) else remark
    }

    fun parseTrojanUrl(url: String): TrojanConfig? {
        return try {
            val uri = URI.create(url)
            val userInfo = uri.userInfo
            val host = uri.host
            val port = uri.port

            if (userInfo != null && host != null && port > 0) {
                val config = TrojanConfig(
                    server = host,
                    port = port,
                    password = userInfo
                )

                // 解析查询参数
                val query = uri.query
                if (query != null) {
                    val params = query.split("&")
                    for (param in params) {
                        val kv = param.split("=")
                        if (kv.size == 2) {
                            when (kv[0]) {
                                "sni" -> config.sni = kv[1]
                                "peer" -> {
                                    // 如果没有 sni，使用 peer
                                    if (config.sni.isEmpty()) {
                                        config.sni = kv[1]
                                    }
                                }
                                "allowInsecure" -> config.allowInsecure = kv[1].toBoolean()
                            }
                        }
                    }
                }

                // 解析备注名称（fragment，# 后面的部分）
                val fragment = uri.fragment
                if (!fragment.isNullOrEmpty()) {
                    try {
                        config.remark = URLDecoder.decode(fragment, "UTF-8")
                    } catch (e: Exception) {
                        config.remark = fragment
                    }
                }

                // 从 remark 中提取区域信息
                config.country = extractCountry(config.remark)

                config
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
