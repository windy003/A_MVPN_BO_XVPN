package com.myvpn.simple.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myvpn.simple.R

class AppExclusionAdapter : RecyclerView.Adapter<AppExclusionAdapter.ViewHolder>() {

    companion object {
        private const val TAG = "AppExclusionAdapter"
    }

    fun interface OnAppExclusionChangeListener {
        fun onAppExclusionChanged(packageName: String, isExcluded: Boolean)
    }

    private var appList: MutableList<AppInfo> = mutableListOf()
    private var filteredAppList: MutableList<AppInfo> = mutableListOf()
    private var listener: OnAppExclusionChangeListener? = null
    private var searchQuery: String = ""

    fun setOnAppExclusionChangeListener(listener: OnAppExclusionChangeListener) {
        this.listener = listener
    }

    fun setAppList(appList: List<AppInfo>) {
        this.appList = appList.toMutableList().apply {
            sortWith(compareBy<AppInfo> { !it.isExcluded }  // 选中的在前面
                .thenBy { it.isSystemApp }  // 用户应用在前面
                .thenBy { it.appName.lowercase() })  // 按应用名排序
        }
        applyFilter()
    }

    fun setSearchQuery(query: String) {
        val newQuery = query.lowercase().trim()
        Log.d(TAG, "setSearchQuery called: '$newQuery' (was: '$searchQuery')")
        if (searchQuery != newQuery) {
            searchQuery = newQuery
            applyFilter()
        }
    }

    private fun applyFilter() {
        val previousSize = filteredAppList.size
        filteredAppList = if (searchQuery.isEmpty()) {
            appList.toMutableList()
        } else {
            appList.filter { app ->
                app.appName.lowercase().contains(searchQuery) ||
                        app.packageName.lowercase().contains(searchQuery)
            }.toMutableList()
        }

        Log.d(TAG, "applyFilter: query='$searchQuery', total=${appList.size}, filtered=${filteredAppList.size}")

        // 使用更精确的通知方法
        if (previousSize == 0 && filteredAppList.isNotEmpty()) {
            notifyItemRangeInserted(0, filteredAppList.size)
        } else if (filteredAppList.isEmpty() && previousSize > 0) {
            notifyItemRangeRemoved(0, previousSize)
        } else {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_app_exclusion, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val app = filteredAppList[position]

        holder.apply {
            appIcon.setImageDrawable(app.icon)
            appName.text = app.appName
            packageName.text = app.packageName

            // 显示应用类型标签
            if (app.isSystemApp) {
                appType.text = "系统"
                appType.visibility = View.VISIBLE
            } else {
                appType.visibility = View.GONE
            }

            checkBox.setOnCheckedChangeListener(null)
            checkBox.isChecked = app.isExcluded

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                app.isExcluded = isChecked
                listener?.onAppExclusionChanged(app.packageName, isChecked)
                // 更新appList中的对应项
                val originalApp = appList.find { it.packageName == app.packageName }
                originalApp?.isExcluded = isChecked
                // 重新排序
                setAppList(appList)
            }

            itemView.setOnClickListener {
                checkBox.isChecked = !checkBox.isChecked
            }
        }
    }

    override fun getItemCount(): Int = filteredAppList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val appIcon: ImageView = itemView.findViewById(R.id.app_icon)
        val appName: TextView = itemView.findViewById(R.id.app_name)
        val packageName: TextView = itemView.findViewById(R.id.package_name)
        val appType: TextView = itemView.findViewById(R.id.app_type)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
    }
}
