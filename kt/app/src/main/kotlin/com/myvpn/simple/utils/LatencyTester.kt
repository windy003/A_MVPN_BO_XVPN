package com.myvpn.simple.utils

import android.util.Log
import java.net.InetSocketAddress
import java.net.Socket

object LatencyTester {
    private const val TAG = "LatencyTester"
    private const val TIMEOUT = 3000 // 3秒超时

    /**
     * 测试节点延迟
     * @param host 服务器地址
     * @param port 端口号
     * @return 延迟时间(ms)，-1表示测试失败
     */
    fun testLatency(host: String, port: Int): Int {
        var socket: Socket? = null
        return try {
            val startTime = System.currentTimeMillis()

            socket = Socket()
            socket.connect(InetSocketAddress(host, port), TIMEOUT)

            val latency = (System.currentTimeMillis() - startTime).toInt()

            Log.d(TAG, "Latency test for $host:$port = ${latency}ms")
            latency
        } catch (e: Exception) {
            Log.e(TAG, "Latency test failed for $host:$port", e)
            -1
        } finally {
            try {
                socket?.close()
            } catch (e: Exception) {
                // 忽略关闭异常
            }
        }
    }

    /**
     * 测试节点延迟（带重试）
     * @param host 服务器地址
     * @param port 端口号
     * @param retries 重试次数，默认1次（总共测试2次取最小值）
     * @return 延迟时间(ms)，-1表示测试失败
     */
    fun testLatencyWithRetry(host: String, port: Int, retries: Int = 1): Int {
        val results = mutableListOf<Int>()

        repeat(retries + 1) {
            val latency = testLatency(host, port)
            if (latency > 0) {
                results.add(latency)
            }
        }

        return if (results.isNotEmpty()) {
            results.minOrNull() ?: -1
        } else {
            -1
        }
    }
}
