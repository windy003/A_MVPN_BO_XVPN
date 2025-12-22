package com.myvpn.simple

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.net.VpnService
import android.os.Bundle
import android.os.IBinder
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.myvpn.simple.database.NodeDatabase
import com.myvpn.simple.database.Subscription
import com.myvpn.simple.database.SubscriptionDao
import com.myvpn.simple.database.TrojanNode
import com.myvpn.simple.database.TrojanNodeDao
import com.myvpn.simple.utils.LatencyTester
import com.myvpn.simple.utils.SubscriptionDownloader

class SubscriptionActivity : AppCompatActivity(),
    SubscriptionAdapter.OnSubscriptionActionListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var rvSubscriptions: RecyclerView
    private lateinit var tvEmptyHint: TextView
    private lateinit var tvFilterHint: TextView
    private lateinit var tvTestProgress: TextView
    private lateinit var btnAddSubscription: Button
    private lateinit var btnTestLatency: Button
    private lateinit var fabConnect: FloatingActionButton
    private lateinit var subscriptionAdapter: SubscriptionAdapter
    private lateinit var nodeAdapter: NodeListAdapter
    private lateinit var subscriptionDao: SubscriptionDao
    private lateinit var nodeDao: TrojanNodeDao

    private var allSubscriptions: List<Subscription> = emptyList()
    private var currentFilteredSubscription: Subscription? = null
    private var isShowingNodes = false
    private var selectedNode: TrojanNode? = null
    private var isTestingLatency = false

    // VPN 服务相关
    private var vpnBinder: SimpleVPNService.VPNBinder? = null
    private var isServiceBound = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            vpnBinder = (service as SimpleVPNService.VPNBinder).also {
                isServiceBound = true
                // 添加状态监听器
                it.addListener(object : SimpleVPNService.VPNStatusListener {
                    override fun onStatusChanged(status: SimpleVPNService.Status) {
                        runOnUiThread { updateFABIcon(status) }
                    }

                    override fun onMessage(message: String) {
                        // 可以选择性地显示消息
                    }
                })
                // 初始化 FAB 图标
                updateFABIcon(it.status)
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {
            vpnBinder = null
            isServiceBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscription)

        initViews()
        initDatabase()
        setupRecyclerView()
        bindVPNService()
        loadSubscriptions()
    }

    private fun bindVPNService() {
        val intent = Intent(this, SimpleVPNService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun initViews() {
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        rvSubscriptions = findViewById(R.id.rv_subscriptions)
        tvEmptyHint = findViewById(R.id.tv_empty_hint)
        tvFilterHint = findViewById(R.id.tv_filter_hint)
        tvTestProgress = findViewById(R.id.tv_test_progress)
        btnAddSubscription = findViewById(R.id.btn_add_subscription)
        btnTestLatency = findViewById(R.id.btn_test_latency)
        fabConnect = findViewById(R.id.fab_connect)

        // 设置工具栏
        findViewById<MaterialToolbar>(R.id.toolbar).setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // 设置导航菜单点击监听
        navigationView.setNavigationItemSelectedListener { menuItem ->
            handleNavigationItemSelected(menuItem)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        btnAddSubscription.setOnClickListener { showAddSubscriptionDialog() }
        btnTestLatency.setOnClickListener { testAllNodesLatency() }

        // 设置悬浮按钮点击监听（切换连接/断开）
        fabConnect.setOnClickListener { toggleVPN() }
    }

    private fun initDatabase() {
        val database = NodeDatabase.getInstance(this)
        subscriptionDao = database.subscriptionDao()
        nodeDao = database.trojanNodeDao()
    }

    private fun setupRecyclerView() {
        subscriptionAdapter = SubscriptionAdapter(this)
        nodeAdapter = NodeListAdapter { node ->
            onNodeClick(node)
        }

        rvSubscriptions.apply {
            layoutManager = LinearLayoutManager(this@SubscriptionActivity)
            adapter = subscriptionAdapter
        }
    }

    private fun loadSubscriptions() {
        Thread {
            val subscriptions = subscriptionDao.getAllSubscriptions()

            // 更新每个订阅的节点数量
            subscriptions.forEach { sub ->
                val count = nodeDao.getNodeCountBySubscription(sub.id)
                if (sub.nodeCount != count) {
                    sub.nodeCount = count
                    subscriptionDao.updateSubscription(sub)
                }
            }

            runOnUiThread {
                allSubscriptions = subscriptions
                updateNavigationMenu(subscriptions)

                if (!isShowingNodes) {
                    subscriptionAdapter.setSubscriptions(subscriptions)
                    tvEmptyHint.visibility = if (subscriptions.isEmpty()) View.VISIBLE else View.GONE
                }
            }
        }.start()
    }

    private fun updateNavigationMenu(subscriptions: List<Subscription>) {
        val menu = navigationView.menu
        menu.clear()

        // 添加"显示所有订阅"选项
        menu.add(0, R.id.nav_all_subscriptions, 0, "显示所有订阅")
            .setIcon(android.R.drawable.ic_menu_view)
            .isCheckable = true

        // 动态添加订阅项
        subscriptions.forEachIndexed { index, subscription ->
            menu.add(0, index + 1000, index + 1, subscription.name)
                .setIcon(android.R.drawable.ic_menu_agenda)
                .isCheckable = true
        }

        // 默认选中"显示所有订阅"
        menu.setGroupCheckable(0, true, true)
        menu.findItem(R.id.nav_all_subscriptions)?.isChecked = true
    }

    private fun handleNavigationItemSelected(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.nav_all_subscriptions -> {
                showAllSubscriptions()
            }
            else -> {
                // 订阅项，ID为 1000 + index
                val index = menuItem.itemId - 1000
                if (index >= 0 && index < allSubscriptions.size) {
                    showSubscriptionNodes(allSubscriptions[index])
                }
            }
        }
    }

    private fun showAllSubscriptions() {
        isShowingNodes = false
        currentFilteredSubscription = null
        selectedNode = null

        rvSubscriptions.adapter = subscriptionAdapter
        subscriptionAdapter.setSubscriptions(allSubscriptions)

        tvFilterHint.visibility = View.GONE
        tvEmptyHint.visibility = if (allSubscriptions.isEmpty()) View.VISIBLE else View.GONE
        btnAddSubscription.visibility = View.VISIBLE
        btnTestLatency.visibility = View.GONE
        tvTestProgress.visibility = View.GONE

        // 隐藏悬浮连接按钮
        fabConnect.hide()
    }

    private fun showSubscriptionNodes(subscription: Subscription) {
        isShowingNodes = true
        currentFilteredSubscription = subscription

        Thread {
            val nodes = nodeDao.getNodesBySubscription(subscription.id)
            val selectedNodeInList = nodes.find { it.isSelected }

            runOnUiThread {
                rvSubscriptions.adapter = nodeAdapter
                nodeAdapter.setNodes(nodes)

                tvFilterHint.text = "订阅: ${subscription.name} (${nodes.size}个节点)"
                tvFilterHint.visibility = View.VISIBLE
                tvEmptyHint.visibility = if (nodes.isEmpty()) View.VISIBLE else View.GONE
                tvEmptyHint.text = "该订阅暂无节点"
                btnAddSubscription.visibility = View.GONE

                // 显示测试延迟按钮（仅当有节点时）
                btnTestLatency.visibility = if (nodes.isNotEmpty()) View.VISIBLE else View.GONE

                // 如果有选中的节点，显示悬浮按钮并记录选中的节点
                if (selectedNodeInList != null) {
                    selectedNode = selectedNodeInList
                    fabConnect.show()
                } else {
                    selectedNode = null
                    fabConnect.hide()
                }
            }
        }.start()
    }

    private fun onNodeClick(node: TrojanNode) {
        Thread {
            // 取消其他节点的选中状态
            nodeDao.clearAllSelections()

            // 设置当前节点为选中
            node.isSelected = true
            nodeDao.updateNode(node)

            runOnUiThread {
                selectedNode = node
                Toast.makeText(this, "已选择节点: ${node.displayName}", Toast.LENGTH_SHORT).show()

                // 显示悬浮连接按钮
                fabConnect.show()

                // 刷新当前订阅的节点列表
                currentFilteredSubscription?.let { showSubscriptionNodes(it) }
            }
        }.start()
    }

    private fun toggleVPN() {
        // 检查 VPN 服务是否已绑定
        val binder = vpnBinder
        if (!isServiceBound || binder == null) {
            Toast.makeText(this, "VPN 服务未就绪", Toast.LENGTH_SHORT).show()
            return
        }

        when (binder.status) {
            SimpleVPNService.Status.CONNECTED -> {
                // 已连接，断开连接
                binder.disconnect()
                Toast.makeText(this, "正在断开连接", Toast.LENGTH_SHORT).show()
            }
            SimpleVPNService.Status.DISCONNECTED -> {
                // 未连接，开始连接
                val node = selectedNode
                if (node == null) {
                    Toast.makeText(this, "请先选择节点", Toast.LENGTH_SHORT).show()
                    return
                }

                // 请求 VPN 权限
                val intent = VpnService.prepare(this)
                if (intent != null) {
                    startActivityForResult(intent, VPN_REQUEST_CODE)
                } else {
                    // 已有权限，直接连接
                    connectVPN()
                }
            }
            SimpleVPNService.Status.CONNECTING -> {
                Toast.makeText(this, "正在连接中，请稍候", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun connectVPN() {
        val node = selectedNode
        if (node == null) {
            Toast.makeText(this, "请先选择节点", Toast.LENGTH_SHORT).show()
            return
        }

        vpnBinder?.let { binder ->
            val config = node.toConfig()
            binder.connect(config)
            Toast.makeText(this, "正在连接节点: ${node.displayName}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateFABIcon(status: SimpleVPNService.Status) {
        when (status) {
            SimpleVPNService.Status.DISCONNECTED -> {
                // 未连接状态：显示连接图标（播放）
                fabConnect.setImageResource(android.R.drawable.ic_media_play)
                fabConnect.contentDescription = "连接节点"
            }
            SimpleVPNService.Status.CONNECTING -> {
                // 连接中状态：显示暂停图标
                fabConnect.setImageResource(android.R.drawable.ic_media_pause)
                fabConnect.contentDescription = "正在连接"
            }
            SimpleVPNService.Status.CONNECTED -> {
                // 已连接状态：显示停止图标
                fabConnect.setImageResource(android.R.drawable.ic_delete)
                fabConnect.contentDescription = "断开连接"
            }
        }
    }

    private fun testAllNodesLatency() {
        if (isTestingLatency) {
            Toast.makeText(this, "正在测试中...", Toast.LENGTH_SHORT).show()
            return
        }

        val subscription = currentFilteredSubscription ?: return

        Thread {
            val nodes = nodeDao.getNodesBySubscription(subscription.id)

            if (nodes.isEmpty()) {
                runOnUiThread {
                    Toast.makeText(this, "没有可测试的节点", Toast.LENGTH_SHORT).show()
                }
                return@Thread
            }

            runOnUiThread {
                isTestingLatency = true
                btnTestLatency.isEnabled = false
                btnTestLatency.text = "测试中..."
                tvTestProgress.visibility = View.VISIBLE
                tvTestProgress.text = "开始测试 ${nodes.size} 个节点"
                Toast.makeText(this, "开始测试 ${nodes.size} 个节点", Toast.LENGTH_SHORT).show()
            }

            var testedCount = 0
            nodes.forEach { node ->
                // 设置为测试中状态（延迟0表示测试中）
                node.latency = 0
                nodeDao.updateNode(node)

                runOnUiThread {
                    // 重新加载节点列表以更新显示
                    refreshCurrentSubscriptionNodes()
                }

                // 测试延迟
                val latency = LatencyTester.testLatency(node.server, node.port)
                node.latency = latency
                nodeDao.updateNode(node)

                testedCount++

                runOnUiThread {
                    // 重新加载节点列表以更新显示
                    refreshCurrentSubscriptionNodes()
                    tvTestProgress.text = "正在测试延迟... ($testedCount/${nodes.size})"
                    btnTestLatency.text = "测试中... ($testedCount/${nodes.size})"
                }
            }

            runOnUiThread {
                isTestingLatency = false
                btnTestLatency.isEnabled = true
                btnTestLatency.text = "测试所有节点延迟"
                tvTestProgress.visibility = View.GONE
                // 最后再加载一次确保显示最新数据
                refreshCurrentSubscriptionNodes()
                Toast.makeText(this, "测试完成！共测试 ${nodes.size} 个节点", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun refreshCurrentSubscriptionNodes() {
        val subscription = currentFilteredSubscription ?: return
        Thread {
            val nodes = nodeDao.getNodesBySubscription(subscription.id)
            runOnUiThread {
                nodeAdapter.setNodes(nodes)
            }
        }.start()
    }

    private fun showAddSubscriptionDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_subscription, null)
        val etName = dialogView.findViewById<EditText>(R.id.et_sub_name)
        val etUrl = dialogView.findViewById<EditText>(R.id.et_sub_url)

        AlertDialog.Builder(this)
            .setTitle("添加订阅")
            .setView(dialogView)
            .setPositiveButton("添加") { _, _ ->
                val name = etName.text.toString().trim()
                val url = etUrl.text.toString().trim()

                if (name.isEmpty() || url.isEmpty()) {
                    Toast.makeText(this, "请填写订阅名称和URL", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                addSubscription(name, url)
            }
            .setNegativeButton("取消", null)
            .show()
    }

    private fun addSubscription(name: String, url: String) {
        Thread {
            val subscription = Subscription(
                name = name,
                url = url,
                updateTime = 0,
                nodeCount = 0
            )

            val subId = subscriptionDao.insertSubscription(subscription)

            runOnUiThread {
                Toast.makeText(this, "订阅已添加，正在更新...", Toast.LENGTH_SHORT).show()
                loadSubscriptions()

                // 自动更新订阅
                subscription.id = subId
                updateSubscription(subscription)
            }
        }.start()
    }

    override fun onSubscriptionClick(subscription: Subscription) {
        // 点击订阅卡片，显示该订阅下的节点
        showSubscriptionNodes(subscription)
    }

    override fun onSubscriptionRename(subscription: Subscription) {
        val etName = EditText(this)
        etName.setText(subscription.name)
        etName.selectAll()

        AlertDialog.Builder(this)
            .setTitle("重命名订阅")
            .setView(etName)
            .setPositiveButton("确定") { _, _ ->
                val newName = etName.text.toString().trim()
                if (newName.isEmpty()) {
                    Toast.makeText(this, "名称不能为空", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                Thread {
                    subscription.name = newName
                    subscriptionDao.updateSubscription(subscription)

                    runOnUiThread {
                        Toast.makeText(this, "已重命名", Toast.LENGTH_SHORT).show()
                        loadSubscriptions()
                    }
                }.start()
            }
            .setNegativeButton("取消", null)
            .show()
    }

    override fun onSubscriptionUpdate(subscription: Subscription) {
        Toast.makeText(this, "正在更新订阅...", Toast.LENGTH_SHORT).show()

        Thread {
            updateSubscription(subscription)
        }.start()
    }

    private fun updateSubscription(subscription: Subscription) {
        // 下载订阅内容
        val content = SubscriptionDownloader.downloadSubscription(subscription.url)

        if (content == null) {
            runOnUiThread {
                Toast.makeText(this, "订阅更新失败", Toast.LENGTH_SHORT).show()
            }
            return
        }

        // 解析订阅
        val configs = SubscriptionParser.parseSubscription(content)

        if (configs.isEmpty()) {
            runOnUiThread {
                Toast.makeText(this, "订阅中没有有效节点", Toast.LENGTH_SHORT).show()
            }
            return
        }

        // 删除该订阅的旧节点
        nodeDao.deleteNodesBySubscription(subscription.id)

        // 添加新节点
        var addedCount = 0
        configs.forEach { config ->
            val node = com.myvpn.simple.database.TrojanNode.fromConfig(config)
            node.subscriptionId = subscription.id
            nodeDao.insertNode(node)
            addedCount++
        }

        // 更新订阅信息
        subscription.updateTime = System.currentTimeMillis()
        subscription.nodeCount = addedCount
        subscriptionDao.updateSubscription(subscription)

        runOnUiThread {
            Toast.makeText(this, "订阅更新成功，共 $addedCount 个节点", Toast.LENGTH_SHORT).show()
            loadSubscriptions()
        }
    }

    override fun onSubscriptionDelete(subscription: Subscription) {
        AlertDialog.Builder(this)
            .setTitle("删除订阅")
            .setMessage("确定要删除订阅 \"${subscription.name}\" 吗？\n这将同时删除该订阅下的所有节点（${subscription.nodeCount}个）")
            .setPositiveButton("删除") { _, _ ->
                Thread {
                    // 删除该订阅的所有节点
                    nodeDao.deleteNodesBySubscription(subscription.id)

                    // 删除订阅
                    subscriptionDao.deleteSubscription(subscription)

                    runOnUiThread {
                        Toast.makeText(this, "订阅已删除", Toast.LENGTH_SHORT).show()
                        loadSubscriptions()
                    }
                }.start()
            }
            .setNegativeButton("取消", null)
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == VPN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                connectVPN()
            } else {
                Toast.makeText(this, "VPN 权限被拒绝", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        if (isServiceBound) {
            unbindService(serviceConnection)
        }
        super.onDestroy()
    }

    override fun onBackPressed() {
        when {
            drawerLayout.isDrawerOpen(GravityCompat.START) -> {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            isShowingNodes -> {
                showAllSubscriptions()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }

    companion object {
        private const val VPN_REQUEST_CODE = 100
    }
}
