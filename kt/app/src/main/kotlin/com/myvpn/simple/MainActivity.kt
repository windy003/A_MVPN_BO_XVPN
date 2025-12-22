package com.myvpn.simple

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.net.VpnService
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.myvpn.simple.database.NodeDatabase
import com.myvpn.simple.database.TrojanNode
import com.myvpn.simple.database.TrojanNodeDao
import com.myvpn.simple.ui.AppExclusionActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvStatus: TextView
    private lateinit var tvMessage: TextView
    private lateinit var tvServerInfo: TextView
    private lateinit var btnManageNodes: Button
    private lateinit var btnSubscriptionManager: Button
    private lateinit var btnAppExclusion: Button
    private lateinit var btnConnect: Button

    private var vpnBinder: SimpleVPNService.VPNBinder? = null
    private var currentConfig: TrojanConfig? = null
    private var isServiceBound = false
    private lateinit var nodeDao: TrojanNodeDao

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            vpnBinder = (service as SimpleVPNService.VPNBinder).also {
                isServiceBound = true
                updateUI(it.status)

                it.addListener(object : SimpleVPNService.VPNStatusListener {
                    override fun onStatusChanged(status: SimpleVPNService.Status) {
                        runOnUiThread { updateUI(status) }
                    }

                    override fun onMessage(message: String) {
                        runOnUiThread { tvMessage.text = message }
                    }
                })
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {
            vpnBinder = null
            isServiceBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initDatabase()
        bindVPNService()
        loadSelectedNode()
    }

    private fun initViews() {
        tvStatus = findViewById(R.id.tv_status)
        tvMessage = findViewById(R.id.tv_message)
        tvServerInfo = findViewById(R.id.tv_server_info)
        btnManageNodes = findViewById(R.id.btn_manage_nodes)
        btnSubscriptionManager = findViewById(R.id.btn_subscription_manager)
        btnAppExclusion = findViewById(R.id.btn_app_exclusion)
        btnConnect = findViewById(R.id.btn_connect)

        btnManageNodes.setOnClickListener { openNodeManager() }
        btnSubscriptionManager.setOnClickListener { openSubscriptionManager() }
        btnAppExclusion.setOnClickListener { openAppExclusion() }
        btnConnect.setOnClickListener { toggleVPN() }
    }

    private fun initDatabase() {
        val database = NodeDatabase.getInstance(this)
        nodeDao = database.trojanNodeDao()
    }

    private fun loadSelectedNode() {
        Thread {
            val selectedNode = nodeDao.getSelectedNode()
            runOnUiThread {
                if (selectedNode != null) {
                    currentConfig = selectedNode.toConfig()
                    tvServerInfo.text = "当前节点: ${selectedNode.displayName}"
                    btnConnect.isEnabled = true
                } else {
                    currentConfig = null
                    tvServerInfo.text = "请选择节点"
                    btnConnect.isEnabled = false
                }
            }
        }.start()
    }

    private fun openNodeManager() {
        startActivityForResult(
            Intent(this, NodesActivity::class.java),
            NODES_REQUEST_CODE
        )
    }

    private fun openAppExclusion() {
        startActivity(Intent(this, AppExclusionActivity::class.java))
    }

    private fun openSubscriptionManager() {
        startActivity(Intent(this, SubscriptionActivity::class.java))
    }

    private fun bindVPNService() {
        val intent = Intent(this, SimpleVPNService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun toggleVPN() {
        val binder = vpnBinder
        if (!isServiceBound || binder == null) {
            Toast.makeText(this, "服务未就绪", Toast.LENGTH_SHORT).show()
            return
        }

        when (binder.status) {
            SimpleVPNService.Status.CONNECTED -> binder.disconnect()
            SimpleVPNService.Status.DISCONNECTED -> {
                if (currentConfig == null) {
                    Toast.makeText(this, "请先粘贴订阅链接", Toast.LENGTH_SHORT).show()
                    return
                }

                val intent = VpnService.prepare(this)
                if (intent != null) {
                    startActivityForResult(intent, VPN_REQUEST_CODE)
                } else {
                    connectVPN()
                }
            }
            else -> {}
        }
    }

    private fun connectVPN() {
        vpnBinder?.let { binder ->
            currentConfig?.let { config ->
                binder.connect(config)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            VPN_REQUEST_CODE -> {
                if (resultCode == RESULT_OK) {
                    connectVPN()
                } else {
                    Toast.makeText(this, "VPN权限被拒绝", Toast.LENGTH_SHORT).show()
                }
            }
            NODES_REQUEST_CODE -> {
                if (resultCode == RESULT_OK) {
                    loadSelectedNode()
                }
            }
        }
    }

    private fun updateUI(status: SimpleVPNService.Status) {
        when (status) {
            SimpleVPNService.Status.DISCONNECTED -> {
                tvStatus.setText(R.string.status_disconnected)
                btnConnect.setText(R.string.connect)
                btnConnect.isEnabled = currentConfig != null
            }

            SimpleVPNService.Status.CONNECTING -> {
                tvStatus.setText(R.string.status_connecting)
                btnConnect.setText(R.string.connect)
                btnConnect.isEnabled = false
            }

            SimpleVPNService.Status.CONNECTED -> {
                tvStatus.setText(R.string.status_connected)
                btnConnect.setText(R.string.disconnect)
                btnConnect.isEnabled = true
            }
        }
    }

    override fun onDestroy() {
        if (isServiceBound) {
            unbindService(serviceConnection)
        }
        super.onDestroy()
    }

    companion object {
        private const val VPN_REQUEST_CODE = 1
        private const val NODES_REQUEST_CODE = 2
    }
}
