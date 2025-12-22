package com.myvpn.simple.utils

import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object SubscriptionDownloader {
    private const val TAG = "SubscriptionDownloader"
    private const val CONNECT_TIMEOUT = 10000 // 10秒
    private const val READ_TIMEOUT = 10000 // 10秒

    /**
     * 从订阅链接下载内容
     * @param url 订阅链接 URL
     * @return 下载的内容（通常是 Base64 编码的节点列表），失败返回 null
     */
    fun downloadSubscription(url: String): String? {
        var connection: HttpURLConnection? = null
        try {
            Log.i(TAG, "开始下载订阅: $url")

            val urlConnection = URL(url)
            connection = urlConnection.openConnection() as HttpURLConnection
            connection.connectTimeout = CONNECT_TIMEOUT
            connection.readTimeout = READ_TIMEOUT
            connection.requestMethod = "GET"

            // 添加常见的请求头
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = StringBuilder()
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()

                val content = response.toString().trim()
                Log.i(TAG, "订阅下载成功，内容长度: ${content.length}")
                return content
            } else {
                Log.e(TAG, "订阅下载失败，HTTP 状态码: $responseCode")
                return null
            }
        } catch (e: Exception) {
            Log.e(TAG, "订阅下载异常", e)
            return null
        } finally {
            connection?.disconnect()
        }
    }

    /**
     * 判断字符串是否为订阅链接 URL
     */
    fun isSubscriptionUrl(text: String): Boolean {
        return text.startsWith("http://", ignoreCase = true) ||
               text.startsWith("https://", ignoreCase = true)
    }
}
