package com.myvpn.simple.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myvpn.simple.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppExclusionAdapter extends RecyclerView.Adapter<AppExclusionAdapter.ViewHolder> {
    
    public interface OnAppExclusionChangeListener {
        void onAppExclusionChanged(String packageName, boolean isExcluded);
    }
    
    private List<AppInfo> appList = new ArrayList<>();
    private List<AppInfo> filteredAppList = new ArrayList<>();
    private OnAppExclusionChangeListener listener;
    private String searchQuery = "";
    
    public void setOnAppExclusionChangeListener(OnAppExclusionChangeListener listener) {
        this.listener = listener;
    }
    
    public void setAppList(List<AppInfo> appList) {
        this.appList = new ArrayList<>(appList);
        Collections.sort(this.appList, (a1, a2) -> {
            // 1. 先按排除状态排序 (选中的在前面)
            if (a1.isExcluded() != a2.isExcluded()) {
                return a1.isExcluded() ? -1 : 1;
            }
            // 2. 再按应用类型排序 (用户应用在前面)
            if (a1.isSystemApp() != a2.isSystemApp()) {
                return a1.isSystemApp() ? 1 : -1;
            }
            // 3. 最后按应用名排序
            return a1.getAppName().compareToIgnoreCase(a2.getAppName());
        });
        applyFilter();
    }
    
    public void setSearchQuery(String query) {
        this.searchQuery = query.toLowerCase();
        applyFilter();
    }
    
    private void applyFilter() {
        filteredAppList.clear();
        for (AppInfo app : appList) {
            if (searchQuery.isEmpty() || 
                app.getAppName().toLowerCase().contains(searchQuery) ||
                app.getPackageName().toLowerCase().contains(searchQuery)) {
                filteredAppList.add(app);
            }
        }
        notifyDataSetChanged();
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_app_exclusion, parent, false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppInfo app = filteredAppList.get(position);
        
        holder.appIcon.setImageDrawable(app.getIcon());
        holder.appName.setText(app.getAppName());
        holder.packageName.setText(app.getPackageName());
        
        // 显示应用类型标签
        if (app.isSystemApp()) {
            holder.appType.setText("系统");
            holder.appType.setVisibility(View.VISIBLE);
        } else {
            holder.appType.setVisibility(View.GONE);
        }
        
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(app.isExcluded());
        
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            app.setExcluded(isChecked);
            if (listener != null) {
                listener.onAppExclusionChanged(app.getPackageName(), isChecked);
            }
            // 重新排序
            setAppList(appList);
        });
        
        holder.itemView.setOnClickListener(v -> {
            holder.checkBox.setChecked(!holder.checkBox.isChecked());
        });
    }
    
    @Override
    public int getItemCount() {
        return filteredAppList.size();
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView appIcon;
        TextView appName;
        TextView packageName;
        TextView appType;
        CheckBox checkBox;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appIcon = itemView.findViewById(R.id.app_icon);
            appName = itemView.findViewById(R.id.app_name);
            packageName = itemView.findViewById(R.id.package_name);
            appType = itemView.findViewById(R.id.app_type);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}