package com.myvpn.simple

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.myvpn.simple.database.TrojanNode

class NodeListAdapter(private val onNodeClick: (TrojanNode) -> Unit) :
    RecyclerView.Adapter<NodeListAdapter.NodeViewHolder>() {

    private var nodes: List<TrojanNode> = emptyList()

    fun setNodes(nodes: List<TrojanNode>) {
        this.nodes = nodes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_node_simple, parent, false)
        return NodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: NodeViewHolder, position: Int) {
        holder.bind(nodes[position])
    }

    override fun getItemCount(): Int = nodes.size

    inner class NodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardNode: MaterialCardView = itemView.findViewById(R.id.card_node)
        private val tvNodeName: TextView = itemView.findViewById(R.id.tv_node_name)
        private val tvNodeInfo: TextView = itemView.findViewById(R.id.tv_node_info)
        private val tvLocation: TextView = itemView.findViewById(R.id.tv_location)
        private val tvLatency: TextView = itemView.findViewById(R.id.tv_latency)
        private val ivSelected: ImageView = itemView.findViewById(R.id.iv_selected)

        fun bind(node: TrojanNode) {
            tvNodeName.text = node.displayName
            tvNodeInfo.text = "${node.server}:${node.port}"
            tvLocation.text = if (node.country.isNotEmpty()) node.country else "未知位置"

            // 显示延迟
            if (node.latency > 0) {
                tvLatency.text = "${node.latency}ms"
                tvLatency.setTextColor(
                    when {
                        node.latency < 100 -> Color.parseColor("#4CAF50")
                        node.latency < 300 -> Color.parseColor("#FF9800")
                        else -> Color.parseColor("#F44336")
                    }
                )
            } else {
                tvLatency.text = ""
            }

            // 设置选中状态的视觉效果
            if (node.isSelected) {
                // 选中状态：紫色边框、浅紫色背景、显示选中图标、增加阴影
                cardNode.strokeWidth = 4
                cardNode.strokeColor = Color.parseColor("#9C27B0")  // 紫色
                cardNode.setCardBackgroundColor(Color.parseColor("#F3E5F5"))  // 浅紫色背景
                cardNode.cardElevation = 8f
                ivSelected.visibility = View.VISIBLE
                itemView.alpha = 1.0f
            } else {
                // 未选中状态：无边框、白色背景、隐藏选中图标、正常阴影
                cardNode.strokeWidth = 0
                cardNode.setCardBackgroundColor(Color.WHITE)
                cardNode.cardElevation = 2f
                ivSelected.visibility = View.GONE
                itemView.alpha = 1.0f
            }

            itemView.setOnClickListener {
                onNodeClick(node)
            }
        }
    }
}
