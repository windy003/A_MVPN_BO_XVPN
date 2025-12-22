package com.myvpn.simple

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import cn.gov.xivpn2.LibXivpn

class MyApplication : Application() {
    companion object {
        private const val TAG = "MyApplication"
    }

    override fun onCreate() {
        super.onCreate()

        try {
            System.loadLibrary("xivpn")
            LibXivpn.xivpn_init()
            Log.i(TAG, "libxivpn initialized successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize libxivpn", e)
        }

        // Create notification channels
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(NotificationManager::class.java)

            val vpnChannel = NotificationChannel(
                "VPN_CHANNEL",
                "VPN Service",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "VPN connection notifications"
            }
            manager.createNotificationChannel(vpnChannel)
        }
    }
}
