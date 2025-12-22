package com.myvpn.simple

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.core.app.NotificationCompat
import cn.gov.xivpn2.LibXivpn
import cn.gov.xivpn2.service.XiVPNService
import com.google.gson.GsonBuilder
import com.myvpn.simple.ui.AppExclusionManager
import com.myvpn.simple.xray.TrojanSettings
import com.myvpn.simple.xray.XrayConfig
import java.io.IOException

class SimpleVPNService : XiVPNService() {

    enum class Status {
        DISCONNECTED,
        CONNECTING,
        CONNECTED
    }

    interface VPNStatusListener {
        fun onStatusChanged(status: Status)
        fun onMessage(message: String)
    }

    inner class VPNBinder : Binder() {
        val service: SimpleVPNService
            get() = this@SimpleVPNService
        val status: Status
            get() = currentStatus
        val appExclusionManager: AppExclusionManager?
            get() = this@SimpleVPNService.appExclusionManager

        fun addListener(listener: VPNStatusListener) = listeners.add(listener)
        fun removeListener(listener: VPNStatusListener) = listeners.remove(listener)
        fun connect(config: TrojanConfig) = startVPN(config)
        fun disconnect() = stopVPN()
    }

    companion object {
        private const val TAG = "SimpleVPNService"
        private const val CHANNEL_ID = "VPN_CHANNEL"
        private const val NOTIFICATION_ID = 1
        private const val SOCKS_PORT = 18964
    }

    private var currentStatus = Status.DISCONNECTED
    private var vpnInterface: ParcelFileDescriptor? = null
    private var currentConfig: TrojanConfig? = null
    private val binder = VPNBinder()
    private val listeners = mutableSetOf<VPNStatusListener>()
    private var appExclusionManager: AppExclusionManager? = null

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        appExclusionManager = AppExclusionManager(this)

        // 检查 SharedPreferences 状态是否与实际状态一致
        // 如果服务被系统杀掉后重启，需要确保状态正确
        val prefs = getSharedPreferences("vpn_status", MODE_PRIVATE)
        val savedStatus = prefs.getString("status", "DISCONNECTED")
        if (savedStatus != "DISCONNECTED" && currentStatus == Status.DISCONNECTED) {
            Log.w(TAG, "Service restarted but SharedPreferences shows $savedStatus, resetting to DISCONNECTED")
            updateStatus(Status.DISCONNECTED)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                "CONNECT" -> {
                    // Android 8.0+ 使用 startForegroundService 时必须立即显示通知
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        startForeground(NOTIFICATION_ID, createNotification("VPN正在连接..."))
                    }

                    val config = it.getSerializableExtra("config") as? TrojanConfig
                    config?.let { conf -> startVPN(conf) }
                }
                "DISCONNECT" -> {
                    Log.d(TAG, "DISCONNECT action received, current status: $currentStatus")

                    // 如果服务已经是 DISCONNECTED 状态（可能是服务重启了），直接确保状态同步
                    if (currentStatus == Status.DISCONNECTED) {
                        Log.d(TAG, "Service already disconnected, ensuring SharedPreferences is updated")
                        updateStatus(Status.DISCONNECTED)
                        stopSelf()
                        return@let
                    }

                    // 断开连接时也需要显示通知（如果是前台服务）
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        startForeground(NOTIFICATION_ID, createNotification("VPN正在断开..."))
                    }
                    stopVPN()
                }
                else -> {
                    // 忽略未知的 action
                }
            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder = binder

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "VPN Service",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    @Synchronized
    private fun startVPN(config: TrojanConfig) {
        if (currentStatus != Status.DISCONNECTED) {
            return
        }

        currentConfig = config
        updateStatus(Status.CONNECTING)

        try {
            // 建立VPN接口
            if (!establishVPN()) {
                updateStatus(Status.DISCONNECTED)
                notifyMessage("VPN接口建立失败")
                return
            }

            // 构建Xray配置
            val xrayConfig = buildXrayConfig(config)

            // 启动libxivpn
            val gson = GsonBuilder().setPrettyPrinting().create()
            val configJson = gson.toJson(xrayConfig)
            Log.i(TAG, "Xray配置: $configJson")

            val result = LibXivpn.xivpn_start(
                configJson,
                SOCKS_PORT,
                vpnInterface!!.fd,
                "", // 不需要日志文件
                filesDir.absolutePath,
                this
            )

            if (result.isNotEmpty()) {
                Log.e(TAG, "libxivpn启动失败: $result")
                updateStatus(Status.DISCONNECTED)
                notifyMessage("代理启动失败: $result")
                try {
                    vpnInterface?.close()
                } catch (e: IOException) {
                    Log.e(TAG, "关闭VPN接口失败", e)
                }
                return
            }

            updateStatus(Status.CONNECTED)
            notifyMessage("VPN连接成功: ${config.server}")

        } catch (e: Exception) {
            Log.e(TAG, "VPN启动失败", e)
            updateStatus(Status.DISCONNECTED)
            notifyMessage("VPN连接异常: ${e.message}")
        }
    }

    private fun establishVPN(): Boolean {
        return try {
            val builder = Builder()
            builder.setMtu(1500)
            builder.addAddress("10.89.64.1", 32)
            builder.addRoute("0.0.0.0", 0)
            builder.addRoute("::", 0)
            builder.addDnsServer("8.8.8.8")
            builder.addDnsServer("8.8.4.4")

            // 应用排除配置
            configureAppExclusion(builder)

            try {
                val appName = packageManager.getApplicationLabel(applicationInfo).toString()
                builder.setSession(appName)
            } catch (e: Exception) {
                builder.setSession("Simple VPN")
            }

            vpnInterface = builder.establish()
            if (vpnInterface == null) {
                return false
            }

            startForeground(NOTIFICATION_ID, createNotification("VPN已连接"))

            true
        } catch (e: Exception) {
            Log.e(TAG, "Failed to establish VPN", e)
            false
        }
    }

    private fun configureAppExclusion(builder: Builder) {
        val manager = appExclusionManager ?: return

        val excludedApps = manager.excludedApps
        val mode = manager.exclusionMode

        Log.i(TAG, "配置应用排除 - 模式: $mode, 应用数量: ${excludedApps.size}")

        for (packageName in excludedApps) {
            try {
                if (packageName == this.packageName) {
                    Log.w(TAG, "跳过本应用包: $packageName")
                    continue
                }

                when (mode) {
                    AppExclusionManager.ExclusionMode.BLACKLIST -> {
                        // 排除模式：列表中的应用不走代理
                        builder.addDisallowedApplication(packageName)
                        Log.i(TAG, "排除应用: $packageName")
                    }
                    AppExclusionManager.ExclusionMode.WHITELIST -> {
                        // 允许模式：只有列表中的应用走代理
                        builder.addAllowedApplication(packageName)
                        Log.i(TAG, "允许应用: $packageName")
                    }
                }
            } catch (e: PackageManager.NameNotFoundException) {
                Log.w(TAG, "应用包不存在: $packageName")
            } catch (e: Exception) {
                Log.e(TAG, "配置应用排除失败: $packageName", e)
            }
        }
    }

    private fun buildXrayConfig(config: TrojanConfig): XrayConfig {
        val xrayConfig = XrayConfig()

        // 创建SOCKS5入站
        val socks5Inbound = XrayConfig.Inbound(
            protocol = "socks",
            port = SOCKS_PORT,
            listen = "10.89.64.1",
            settings = mapOf("udp" to true),
            sniffing = XrayConfig.Sniffing(
                enabled = true,
                destOverride = listOf("http", "tls"),
                routeOnly = true
            )
        )

        // 创建Trojan出站
        val trojanSettings = TrojanSettings(
            servers = mutableListOf(
                TrojanSettings.TrojanServer(
                    address = config.server,
                    port = config.port,
                    password = config.password
                )
            )
        )

        val trojanOutbound = XrayConfig.Outbound(
            tag = "proxy",
            protocol = "trojan",
            settings = trojanSettings,
            streamSettings = XrayConfig.StreamSettings(
                network = "tcp",
                security = "tls",
                tlsSettings = XrayConfig.TlsSettings(
                    serverName = config.server,
                    allowInsecure = true
                )
            )
        )

        // 创建直连出站
        val directOutbound = XrayConfig.Outbound(
            tag = "direct",
            protocol = "freedom",
            settings = emptyMap<String, Any>()
        )

        // 创建路由规则
        val routing = XrayConfig.Routing(
            rules = listOf(
                XrayConfig.RoutingRule(
                    type = "field",
                    outboundTag = "proxy",
                    network = "tcp,udp"
                )
            )
        )

        xrayConfig.inbounds = listOf(socks5Inbound)
        xrayConfig.outbounds = listOf(trojanOutbound, directOutbound)
        xrayConfig.routing = routing

        return xrayConfig
    }

    @Synchronized
    private fun stopVPN() {
        if (currentStatus == Status.DISCONNECTED) {
            return
        }

        // 停止libxivpn
        try {
            LibXivpn.xivpn_stop()
        } catch (e: Exception) {
            Log.e(TAG, "停止libxivpn失败", e)
        }

        // 关闭VPN接口
        vpnInterface?.let {
            try {
                it.close()
            } catch (e: IOException) {
                Log.e(TAG, "关闭VPN接口失败", e)
            }
            vpnInterface = null
        }

        updateStatus(Status.DISCONNECTED)
        stopForeground(true)
        notifyMessage("VPN已断开")

        stopSelf()
    }

    private fun updateStatus(status: Status) {
        currentStatus = status
        listeners.forEach { it.onStatusChanged(status) }

        // 保存状态到 SharedPreferences 供 TileService 使用
        val prefs = getSharedPreferences("vpn_status", MODE_PRIVATE)
        prefs.edit().apply {
            putBoolean("is_connected", status == Status.CONNECTED)
            putString("status", status.name) // 存储详细状态
        }.apply()

        Log.d(TAG, "Status updated to: $status")

        // 通知 TileService 更新磁贴
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                android.service.quicksettings.TileService.requestListeningState(
                    this,
                    android.content.ComponentName(this, VPNTileService::class.java)
                )
            } catch (e: Exception) {
                Log.e(TAG, "Failed to update tile", e)
            }
        }
    }

    private fun notifyMessage(message: String) {
        Log.i(TAG, message)
        listeners.forEach { it.onMessage(message) }
    }

    private fun createNotification(text: String): Notification {
        val intent = Intent(this, MainActivity::class.java)
        var flags = PendingIntent.FLAG_UPDATE_CURRENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flags = flags or PendingIntent.FLAG_IMMUTABLE
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, flags)

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Simple VPN")
            .setContentText(text)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()
    }

    override fun onRevoke() {
        Log.i(TAG, "VPN权限被撤销")
        stopVPN()
        super.onRevoke()
    }

    override fun onDestroy() {
        stopVPN()
        super.onDestroy()
    }
}
