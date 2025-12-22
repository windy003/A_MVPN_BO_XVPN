package com.myvpn.simple

import android.content.ClipboardManager
import android.content.Context
import android.util.Base64
import com.myvpn.simple.utils.SubscriptionDownloader

object ClipboardHelper {

    fun readSubscriptionFromClipboard(context: Context): List<TrojanConfig> {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
            ?: return emptyList()

        if (!clipboard.hasPrimaryClip()) {
            return emptyList()
        }

        val clipData = clipboard.primaryClip ?: return emptyList()
        if (clipData.itemCount == 0) {
            return emptyList()
        }

        val text = clipData.getItemAt(0).text?.toString()?.trim() ?: return emptyList()

        return when {
            // 单个 Trojan URL
            text.startsWith("trojan://") -> {
                val config = SubscriptionParser.parseTrojanUrl(text)
                if (config != null && config.isValid()) {
                    listOf(config)
                } else {
                    emptyList()
                }
            }
            // 订阅链接 URL
            SubscriptionDownloader.isSubscriptionUrl(text) -> {
                val content = SubscriptionDownloader.downloadSubscription(text)
                if (content != null) {
                    SubscriptionParser.parseSubscription(content)
                } else {
                    emptyList()
                }
            }
            // Base64 编码的订阅内容
            isBase64(text) -> {
                SubscriptionParser.parseSubscription(text)
            }
            else -> emptyList()
        }
    }

    private fun isBase64(str: String): Boolean {
        return try {
            val decoded = String(Base64.decode(str, Base64.DEFAULT))
            decoded.contains("trojan://")
        } catch (e: Exception) {
            false
        }
    }
}
