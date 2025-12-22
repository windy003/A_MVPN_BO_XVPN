package com.myvpn.simple

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myvpn.simple.database.Subscription
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SubscriptionAdapter(
    private val listener: OnSubscriptionActionListener
) : RecyclerView.Adapter<SubscriptionAdapter.SubscriptionViewHolder>() {

    private var subscriptions: List<Subscription> = emptyList()

    interface OnSubscriptionActionListener {
        fun onSubscriptionClick(subscription: Subscription)
        fun onSubscriptionRename(subscription: Subscription)
        fun onSubscriptionUpdate(subscription: Subscription)
        fun onSubscriptionDelete(subscription: Subscription)
    }

    fun setSubscriptions(subscriptions: List<Subscription>) {
        this.subscriptions = subscriptions
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subscription, parent, false)
        return SubscriptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubscriptionViewHolder, position: Int) {
        holder.bind(subscriptions[position])
    }

    override fun getItemCount(): Int = subscriptions.size

    inner class SubscriptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_sub_name)
        private val tvNodeCount: TextView = itemView.findViewById(R.id.tv_node_count)
        private val tvUpdateTime: TextView = itemView.findViewById(R.id.tv_update_time)
        private val btnRename: ImageButton = itemView.findViewById(R.id.btn_rename)
        private val btnUpdate: ImageButton = itemView.findViewById(R.id.btn_update)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btn_delete)

        fun bind(subscription: Subscription) {
            tvName.text = subscription.name
            tvNodeCount.text = "${subscription.nodeCount} 个节点"

            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            val updateTimeText = if (subscription.updateTime > 0) {
                "更新: ${sdf.format(Date(subscription.updateTime))}"
            } else {
                "未更新"
            }
            tvUpdateTime.text = updateTimeText

            itemView.setOnClickListener {
                listener.onSubscriptionClick(subscription)
            }

            btnRename.setOnClickListener {
                listener.onSubscriptionRename(subscription)
            }

            btnUpdate.setOnClickListener {
                listener.onSubscriptionUpdate(subscription)
            }

            btnDelete.setOnClickListener {
                listener.onSubscriptionDelete(subscription)
            }
        }
    }
}
