package com.myvpn.simple

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.myvpn.simple.database.NodeDatabase
import com.myvpn.simple.database.TrojanNode
import com.myvpn.simple.database.TrojanNodeDao
import com.myvpn.simple.utils.LatencyTester

class NodesActivity : AppCompatActivity(), NodeAdapter.OnNodeActionListener {

    private lateinit var rvNodes: RecyclerView
    private lateinit var tvEmptyHint: TextView
    private lateinit var btnAddFromClipboard: Button
    private lateinit var btnTestLatency: Button
    private lateinit var btnDeleteAll: Button
    private lateinit var adapter: NodeAdapter
    private lateinit var nodeDao: TrojanNodeDao
    private var isTesting = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nodes)

        initViews()
        initDatabase()
        setupRecyclerView()
        loadNodes()
    }

    private fun initViews() {
        findViewById<MaterialToolbar>(R.id.toolbar).setNavigationOnClickListener { finish() }

        rvNodes = findViewById(R.id.rv_nodes)
        tvEmptyHint = findViewById(R.id.tv_empty_hint)
        btnAddFromClipboard = findViewById(R.id.btn_add_from_clipboard)
        btnTestLatency = findViewById(R.id.btn_test_latency)
        btnDeleteAll = findViewById(R.id.btn_delete_all)

        btnAddFromClipboard.setOnClickListener { addFromClipboard() }
        btnTestLatency.setOnClickListener { testAllNodesLatency() }
        btnDeleteAll.setOnClickListener { deleteAllNodes() }
    }

    private fun initDatabase() {
        val database = NodeDatabase.getInstance(this)
        nodeDao = database.trojanNodeDao()
    }

    private fun setupRecyclerView() {
        adapter = NodeAdapter(this)
        rvNodes.apply {
            layoutManager = LinearLayoutManager(this@NodesActivity)
            adapter = this@NodesActivity.adapter
        }
    }

    private fun loadNodes() {
        Thread {
            val nodes = nodeDao.getAllNodes()
            runOnUiThread {
                adapter.setNodes(nodes)
                tvEmptyHint.visibility = if (nodes.isEmpty()) View.VISIBLE else View.GONE
            }
        }.start()
    }

    private fun addFromClipboard() {
        // 显示加载提示
        Toast.makeText(this, "正在解析内容...", Toast.LENGTH_SHORT).show()

        // 在后台线程中处理（可能需要网络请求）
        Thread {
            val configs = ClipboardHelper.readSubscriptionFromClipboard(this)

            runOnUiThread {
                if (configs.isEmpty()) {
                    Toast.makeText(this, "剪贴板中没有找到有效的Trojan链接", Toast.LENGTH_SHORT).show()
                    return@runOnUiThread
                }

                when {
                    configs.size == 1 -> addSingleNode(configs[0])
                    else -> showMultipleNodesDialog(configs)
                }
            }
        }.start()
    }

    private fun createNodeWithLocation(config: TrojanConfig): TrojanNode {
        val node = TrojanNode.fromConfig(config)

        // 如果有备注名称，直接使用
        if (config.remark.isNotEmpty()) {
            node.name = config.remark
        }

        return node
    }

    private fun addSingleNode(config: TrojanConfig) {
        Thread {
            val node = createNodeWithLocation(config)
            nodeDao.insertNode(node)

            runOnUiThread {
                Toast.makeText(this, "节点添加成功", Toast.LENGTH_SHORT).show()
                loadNodes()
            }
        }.start()
    }

    private fun showMultipleNodesDialog(configs: List<TrojanConfig>) {
        AlertDialog.Builder(this)
            .setTitle("发现多个节点")
            .setMessage("从剪贴板中发现 ${configs.size} 个节点，是否全部添加？")
            .setPositiveButton("全部添加") { _, _ ->
                Thread {
                    var addedCount = 0
                    configs.forEach { config ->
                        val node = createNodeWithLocation(config)
                        nodeDao.insertNode(node)
                        addedCount++
                    }

                    runOnUiThread {
                        Toast.makeText(this, "成功添加 $addedCount 个节点", Toast.LENGTH_SHORT).show()
                        loadNodes()
                    }
                }.start()
            }
            .setNeutralButton("添加第一个") { _, _ -> addSingleNode(configs[0]) }
            .setNegativeButton("取消", null)
            .show()
    }

    override fun onNodeSelected(node: TrojanNode) {
        Thread {
            nodeDao.clearAllSelections()
            nodeDao.selectNode(node.id)

            runOnUiThread {
                Toast.makeText(this, "已选择节点: ${node.displayName}", Toast.LENGTH_SHORT).show()
                setResult(RESULT_OK)
            }
        }.start()
    }

    override fun onNodeDeleted(node: TrojanNode) {
        AlertDialog.Builder(this)
            .setTitle("删除节点")
            .setMessage("确定要删除节点 \"${node.displayName}\" 吗？")
            .setPositiveButton("删除") { _, _ ->
                Thread {
                    nodeDao.deleteNode(node)
                    runOnUiThread {
                        Toast.makeText(this, "节点已删除", Toast.LENGTH_SHORT).show()
                        loadNodes()
                    }
                }.start()
            }
            .setNegativeButton("取消", null)
            .show()
    }

    private fun testAllNodesLatency() {
        if (isTesting) {
            Toast.makeText(this, "正在测试中...", Toast.LENGTH_SHORT).show()
            return
        }

        Thread {
            val nodes = nodeDao.getAllNodes()

            if (nodes.isEmpty()) {
                runOnUiThread {
                    Toast.makeText(this, "没有可测试的节点", Toast.LENGTH_SHORT).show()
                }
                return@Thread
            }

            runOnUiThread {
                isTesting = true
                btnTestLatency.isEnabled = false
                btnTestLatency.text = "测试中..."
                Toast.makeText(this, "开始测试 ${nodes.size} 个节点", Toast.LENGTH_SHORT).show()
            }

            var testedCount = 0
            nodes.forEach { node ->
                // 设置为测试中状态
                node.latency = 0
                nodeDao.updateNode(node)

                runOnUiThread {
                    loadNodes() // 重新加载节点列表以更新显示
                }

                // 测试延迟
                val latency = LatencyTester.testLatency(node.server, node.port)
                node.latency = latency
                nodeDao.updateNode(node)

                testedCount++

                runOnUiThread {
                    loadNodes() // 重新加载节点列表以更新显示
                    btnTestLatency.text = "测试中... ($testedCount/${nodes.size})"
                }
            }

            runOnUiThread {
                isTesting = false
                btnTestLatency.isEnabled = true
                btnTestLatency.text = "测试延迟"
                loadNodes() // 最后再加载一次确保显示最新数据
                Toast.makeText(this, "测试完成", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun deleteAllNodes() {
        Thread {
            val nodeCount = nodeDao.getAllNodes().size

            if (nodeCount == 0) {
                runOnUiThread {
                    Toast.makeText(this, "暂无节点可删除", Toast.LENGTH_SHORT).show()
                }
                return@Thread
            }

            runOnUiThread {
                AlertDialog.Builder(this)
                    .setTitle("删除全部节点")
                    .setMessage("确定要删除全部 $nodeCount 个节点吗？此操作不可撤销！")
                    .setPositiveButton("删除") { _, _ ->
                        Thread {
                            // 执行删除操作
                            nodeDao.deleteAllNodes()

                            runOnUiThread {
                                Toast.makeText(this, "已删除全部 $nodeCount 个节点", Toast.LENGTH_SHORT).show()
                                loadNodes()
                            }
                        }.start()
                    }
                    .setNegativeButton("取消", null)
                    .show()
            }
        }.start()
    }
}
