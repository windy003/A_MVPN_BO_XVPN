package com.myvpn.simple;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0014B\u0019\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u00a2\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00020\u00062\n\u0010\r\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u001c\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0016J\u0014\u0010\u0013\u001a\u00020\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tR\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/myvpn/simple/NodeListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/myvpn/simple/NodeListAdapter$NodeViewHolder;", "onNodeClick", "Lkotlin/Function1;", "Lcom/myvpn/simple/database/TrojanNode;", "", "(Lkotlin/jvm/functions/Function1;)V", "nodes", "", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setNodes", "NodeViewHolder", "app_debug"})
public final class NodeListAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.myvpn.simple.NodeListAdapter.NodeViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.myvpn.simple.database.TrojanNode, kotlin.Unit> onNodeClick = null;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.myvpn.simple.database.TrojanNode> nodes;
    
    public NodeListAdapter(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.myvpn.simple.database.TrojanNode, kotlin.Unit> onNodeClick) {
        super();
    }
    
    public final void setNodes(@org.jetbrains.annotations.NotNull
    java.util.List<com.myvpn.simple.database.TrojanNode> nodes) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.myvpn.simple.NodeListAdapter.NodeViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.myvpn.simple.NodeListAdapter.NodeViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/myvpn/simple/NodeListAdapter$NodeViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/myvpn/simple/NodeListAdapter;Landroid/view/View;)V", "cardNode", "Lcom/google/android/material/card/MaterialCardView;", "ivSelected", "Landroid/widget/ImageView;", "tvLatency", "Landroid/widget/TextView;", "tvLocation", "tvNodeInfo", "tvNodeName", "bind", "", "node", "Lcom/myvpn/simple/database/TrojanNode;", "app_debug"})
    public final class NodeViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.google.android.material.card.MaterialCardView cardNode = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvNodeName = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvNodeInfo = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvLocation = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvLatency = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView ivSelected = null;
        
        public NodeViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.myvpn.simple.database.TrojanNode node) {
        }
    }
}