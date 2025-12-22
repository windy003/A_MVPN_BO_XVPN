package com.myvpn.simple

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myvpn.simple.database.TrojanNode
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NodeAdapter(
    private val listener: OnNodeActionListener
) : RecyclerView.Adapter<NodeAdapter.NodeViewHolder>() {

    private var nodes: List<TrojanNode> = emptyList()
    private var selectedNodeId: Long = -1

    interface OnNodeActionListener {
        fun onNodeSelected(node: TrojanNode)
        fun onNodeDeleted(node: TrojanNode)
    }

    fun setNodes(nodes: List<TrojanNode>) {
        this.nodes = nodes
        selectedNodeId = nodes.find { it.isSelected }?.id ?: -1
        notifyDataSetChanged()
    }

    fun setSelectedNodeId(nodeId: Long) {
        selectedNodeId = nodeId
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_node, parent, false)
        return NodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: NodeViewHolder, position: Int) {
        holder.bind(nodes[position])
    }

    override fun getItemCount(): Int = nodes.size

    inner class NodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvServerInfo: TextView = itemView.findViewById(R.id.tv_server_info)
        private val tvCreateTime: TextView = itemView.findViewById(R.id.tv_create_time)
        private val tvLatency: TextView? = itemView.findViewById(R.id.tv_latency)
        private val rbSelect: RadioButton = itemView.findViewById(R.id.rb_select)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btn_delete)

        fun bind(node: TrojanNode) {
            tvName.text = node.displayName
            tvServerInfo.text = "${node.server}:${node.port}"

            val sdf = SimpleDateFormat("MM-dd HH:mm", Locale.getDefault())
            tvCreateTime.text = sdf.format(Date(node.createTime))

            // 显示延迟信息
            tvLatency?.let { latencyView ->
                when {
                    node.latency < 0 -> {
                        latencyView.text = "未测试"
                        latencyView.setTextColor(0xFF999999.toInt())
                    }
                    node.latency == 0 -> {
                        latencyView.text = "测试中..."
                        latencyView.setTextColor(0xFF666666.toInt())
                    }
                    node.latency > 0 -> {
                        latencyView.text = "${node.latency}ms"
                        // 根据延迟设置颜色
                        val color = when {
                            node.latency < 100 -> 0xFF00CC00.toInt()  // 绿色：优秀
                            node.latency < 300 -> 0xFFFFAA00.toInt()  // 橙色：良好
                            else -> 0xFFFF0000.toInt()                // 红色：较慢
                        }
                        latencyView.setTextColor(color)
                    }
                }
            }

            rbSelect.isChecked = node.id == selectedNodeId

            rbSelect.setOnClickListener {
                selectedNodeId = node.id
                listener.onNodeSelected(node)
                notifyDataSetChanged()
            }

            btnDelete.setOnClickListener {
                listener.onNodeDeleted(node)
            }

            itemView.setOnClickListener {
                selectedNodeId = node.id
                listener.onNodeSelected(node)
                notifyDataSetChanged()
            }
        }
    }
}
