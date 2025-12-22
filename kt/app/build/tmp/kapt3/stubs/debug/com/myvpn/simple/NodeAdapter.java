package com.myvpn.simple;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002\u0018\u0019B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\fH\u0016J\u001c\u0010\u0011\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\fH\u0016J\u0014\u0010\u0015\u001a\u00020\u000e2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u000e\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/myvpn/simple/NodeAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/myvpn/simple/NodeAdapter$NodeViewHolder;", "listener", "Lcom/myvpn/simple/NodeAdapter$OnNodeActionListener;", "(Lcom/myvpn/simple/NodeAdapter$OnNodeActionListener;)V", "nodes", "", "Lcom/myvpn/simple/database/TrojanNode;", "selectedNodeId", "", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setNodes", "setSelectedNodeId", "nodeId", "NodeViewHolder", "OnNodeActionListener", "app_debug"})
public final class NodeAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.myvpn.simple.NodeAdapter.NodeViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final com.myvpn.simple.NodeAdapter.OnNodeActionListener listener = null;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.myvpn.simple.database.TrojanNode> nodes;
    private long selectedNodeId = -1L;
    
    public NodeAdapter(@org.jetbrains.annotations.NotNull
    com.myvpn.simple.NodeAdapter.OnNodeActionListener listener) {
        super();
    }
    
    public final void setNodes(@org.jetbrains.annotations.NotNull
    java.util.List<com.myvpn.simple.database.TrojanNode> nodes) {
    }
    
    public final void setSelectedNodeId(long nodeId) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.myvpn.simple.NodeAdapter.NodeViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.myvpn.simple.NodeAdapter.NodeViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/myvpn/simple/NodeAdapter$NodeViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/myvpn/simple/NodeAdapter;Landroid/view/View;)V", "btnDelete", "Landroid/widget/ImageButton;", "rbSelect", "Landroid/widget/RadioButton;", "tvCreateTime", "Landroid/widget/TextView;", "tvLatency", "tvName", "tvServerInfo", "bind", "", "node", "Lcom/myvpn/simple/database/TrojanNode;", "app_debug"})
    public final class NodeViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvName = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvServerInfo = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvCreateTime = null;
        @org.jetbrains.annotations.Nullable
        private final android.widget.TextView tvLatency = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.RadioButton rbSelect = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageButton btnDelete = null;
        
        public NodeViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.myvpn.simple.database.TrojanNode node) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0007"}, d2 = {"Lcom/myvpn/simple/NodeAdapter$OnNodeActionListener;", "", "onNodeDeleted", "", "node", "Lcom/myvpn/simple/database/TrojanNode;", "onNodeSelected", "app_debug"})
    public static abstract interface OnNodeActionListener {
        
        public abstract void onNodeSelected(@org.jetbrains.annotations.NotNull
        com.myvpn.simple.database.TrojanNode node);
        
        public abstract void onNodeDeleted(@org.jetbrains.annotations.NotNull
        com.myvpn.simple.database.TrojanNode node);
    }
}