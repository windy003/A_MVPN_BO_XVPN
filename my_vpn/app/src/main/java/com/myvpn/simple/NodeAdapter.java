package com.myvpn.simple;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.myvpn.simple.database.TrojanNode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NodeAdapter extends RecyclerView.Adapter<NodeAdapter.NodeViewHolder> {
    
    private List<TrojanNode> nodes = new ArrayList<>();
    private OnNodeActionListener listener;
    private long selectedNodeId = -1;
    
    public interface OnNodeActionListener {
        void onNodeSelected(TrojanNode node);
        void onNodeDeleted(TrojanNode node);
    }
    
    public NodeAdapter(OnNodeActionListener listener) {
        this.listener = listener;
    }
    
    public void setNodes(List<TrojanNode> nodes) {
        this.nodes = nodes;
        for (TrojanNode node : nodes) {
            if (node.isSelected) {
                selectedNodeId = node.id;
                break;
            }
        }
        notifyDataSetChanged();
    }
    
    public void setSelectedNodeId(long nodeId) {
        this.selectedNodeId = nodeId;
        notifyDataSetChanged();
    }
    
    @NonNull
    @Override
    public NodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_node, parent, false);
        return new NodeViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull NodeViewHolder holder, int position) {
        TrojanNode node = nodes.get(position);
        holder.bind(node);
    }
    
    @Override
    public int getItemCount() {
        return nodes.size();
    }
    
    class NodeViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvServerInfo, tvCreateTime;
        RadioButton rbSelect;
        ImageButton btnDelete;
        
        NodeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvServerInfo = itemView.findViewById(R.id.tv_server_info);
            tvCreateTime = itemView.findViewById(R.id.tv_create_time);
            rbSelect = itemView.findViewById(R.id.rb_select);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
        
        void bind(TrojanNode node) {
            tvName.setText(node.getDisplayName());
            tvServerInfo.setText(node.server + ":" + node.port);
            
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());
            tvCreateTime.setText(sdf.format(new Date(node.createTime)));
            
            rbSelect.setChecked(node.id == selectedNodeId);
            
            rbSelect.setOnClickListener(v -> {
                if (listener != null) {
                    selectedNodeId = node.id;
                    listener.onNodeSelected(node);
                    notifyDataSetChanged();
                }
            });
            
            btnDelete.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onNodeDeleted(node);
                }
            });
            
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    selectedNodeId = node.id;
                    listener.onNodeSelected(node);
                    notifyDataSetChanged();
                }
            });
        }
    }
}