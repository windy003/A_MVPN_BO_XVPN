package com.myvpn.simple

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.drawable.Icon
import android.net.VpnService
import android.os.Build
import android.os.IBinder
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.util.Log
import androidx.annotation.RequiresApi
import com.myvpn.simple.database.NodeDatabase

@RequiresApi(Build.VERSION_CODES.N)
class VPNTileService : TileService() {

    private var vpnService: SimpleVPNService? = null
    private var isServiceBound = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as SimpleVPNService.VPNBinder
            vpnService = binder.service
            isServiceBound = true
            updateTile()
        }

        override fun onServiceDisconnected(name: ComponentName) {
            vpnService = null
            isServiceBound = false
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "VPNTileService created")
    }

    override fun onStartListening() {
        super.onStartListening()
        Log.d(TAG, "Tile start listening")

        // 绑定 VPN 服务以获取状态
        try {
            val serviceIntent = Intent(this, SimpleVPNService::class.java)
            bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to bind service", e)
            updateTile()
        }
    }

    override fun onStopListening() {
        super.onStopListening()
        Log.d(TAG, "Tile stop listening")

        if (isServiceBound) {
            unbindService(serviceConnection)
            isServiceBound = false
        }
    }

    override fun onClick() {
        super.onClick()
        Log.d(TAG, "Tile clicked")

        // 从 SharedPreferences 读取详细的连接状态
        val prefs = getSharedPreferences("vpn_status", Context.MODE_PRIVATE)
        val statusString = prefs.getString("status", "DISCONNECTED") ?: "DISCONNECTED"

        Log.d(TAG, "Current VPN status: $statusString")
        Log.d(TAG, "Service bound: $isServiceBound")

        // 根据当前状态决定操作
        // CONNECTED -> 断开
        // DISCONNECTED -> 连接
        // CONNECTING -> 忽略（正在连接中，避免重复操作）
        when (statusString) {
            "CONNECTED" -> {
                Log.d(TAG, "VPN is connected, disconnecting...")
                disconnectVPN()
            }
            "DISCONNECTED" -> {
                Log.d(TAG, "VPN is disconnected, connecting...")
                connectVPN()
            }
            "CONNECTING" -> {
                // 正在连接中，忽略点击避免重复操作
                Log.w(TAG, "VPN is connecting, ignoring click to avoid duplicate operation")
            }
            else -> {
                // 未知状态，默认尝试断开
                Log.w(TAG, "Unknown status: $statusString, attempting disconnect")
                disconnectVPN()
            }
        }
    }

    private fun connectVPN() {
        // 立即更新 SharedPreferences 状态，防止重复点击
        val prefs = getSharedPreferences("vpn_status", Context.MODE_PRIVATE)
        prefs.edit().putString("status", "CONNECTING").apply()
        Log.d(TAG, "SharedPreferences immediately updated to CONNECTING")

        // 立即更新磁贴为连接中状态，提供即时视觉反馈
        qsTile?.let { tile ->
            tile.state = Tile.STATE_ACTIVE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                tile.subtitle = "连接中..."
            }
            tile.updateTile()
            Log.d(TAG, "Tile immediately updated to connecting state")
        }

        Thread {
            try {
                // 检查 VPN 权限
                val prepareIntent = VpnService.prepare(this)
                if (prepareIntent != null) {
                    Log.w(TAG, "VPN permission not granted, opening main activity")
                    // 重置状态
                    resetToDisconnected()
                    // 没有 VPN 权限，打开主界面让用户授权
                    val intent = Intent(this, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    startActivity(intent)
                    return@Thread
                }

                // 获取选中的节点
                val nodeDao = NodeDatabase.getInstance(this).trojanNodeDao()
                val selectedNode = nodeDao.getSelectedNode()

                if (selectedNode == null) {
                    Log.w(TAG, "No selected node, opening main activity")
                    // 重置状态
                    resetToDisconnected()
                    // 没有选中的节点，打开主界面
                    val intent = Intent(this, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    startActivity(intent)
                    return@Thread
                }

                // 启动 VPN 服务
                val config = selectedNode.toConfig()
                val serviceIntent = Intent(this, SimpleVPNService::class.java).apply {
                    action = "CONNECT"
                    putExtra("config", config)
                }

                // 使用 startForegroundService (Android 8.0+)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(serviceIntent)
                } else {
                    startService(serviceIntent)
                }

                // 不需要在这里更新磁贴，SimpleVPNService 会通过 requestListeningState 触发 onStartListening 来更新磁贴
            } catch (e: Exception) {
                Log.e(TAG, "Failed to connect VPN", e)
                resetToDisconnected()
            }
        }.start()
    }

    private fun resetToDisconnected() {
        // 重置 SharedPreferences 状态
        val prefs = getSharedPreferences("vpn_status", Context.MODE_PRIVATE)
        prefs.edit().putString("status", "DISCONNECTED").apply()
        Log.d(TAG, "State reset to DISCONNECTED")

        // 更新磁贴
        qsTile?.let { tile ->
            tile.state = Tile.STATE_INACTIVE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                tile.subtitle = "未连接"
            }
            tile.updateTile()
            Log.d(TAG, "Tile updated to disconnected state")
        }
    }

    private fun disconnectVPN() {
        try {
            // 立即更新 SharedPreferences 状态，防止重复点击
            val prefs = getSharedPreferences("vpn_status", Context.MODE_PRIVATE)
            prefs.edit().putString("status", "DISCONNECTED").apply()
            Log.d(TAG, "SharedPreferences immediately updated to DISCONNECTED")

            // 立即更新磁贴为断开中状态，提供即时视觉反馈
            qsTile?.let { tile ->
                tile.state = Tile.STATE_INACTIVE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    tile.subtitle = "断开中..."
                }
                tile.updateTile()
                Log.d(TAG, "Tile immediately updated to disconnecting state")
            }

            val serviceIntent = Intent(this, SimpleVPNService::class.java).apply {
                action = "DISCONNECT"
            }

            // 使用 startForegroundService (Android 8.0+)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(serviceIntent)
            } else {
                startService(serviceIntent)
            }

            // SimpleVPNService 完成断开后会通过 requestListeningState 触发最终的磁贴更新
        } catch (e: Exception) {
            Log.e(TAG, "Failed to disconnect VPN", e)
        }
    }

    private fun updateTile() {
        val tile = qsTile ?: return

        try {
            // 从 SharedPreferences 读取 VPN 详细状态
            val prefs = getSharedPreferences("vpn_status", Context.MODE_PRIVATE)
            val statusString = prefs.getString("status", "DISCONNECTED") ?: "DISCONNECTED"

            tile.label = "FastVPN"

            when (statusString) {
                "CONNECTED" -> {
                    tile.state = Tile.STATE_ACTIVE
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        tile.subtitle = "已连接"
                    }
                }
                "CONNECTING" -> {
                    tile.state = Tile.STATE_ACTIVE
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        tile.subtitle = "连接中..."
                    }
                }
                "DISCONNECTED" -> {
                    tile.state = Tile.STATE_INACTIVE
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        tile.subtitle = "未连接"
                    }
                }
                else -> {
                    tile.state = Tile.STATE_INACTIVE
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        tile.subtitle = "未知状态"
                    }
                }
            }

            // 设置字母 F 图标
            tile.icon = Icon.createWithResource(this, R.drawable.ic_tile_f)

            tile.updateTile()
            Log.d(TAG, "Tile updated with status: $statusString")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to update tile", e)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isServiceBound) {
            try {
                unbindService(serviceConnection)
            } catch (e: Exception) {
                Log.e(TAG, "Failed to unbind service", e)
            }
            isServiceBound = false
        }
    }

    companion object {
        private const val TAG = "VPNTileService"
    }
}
