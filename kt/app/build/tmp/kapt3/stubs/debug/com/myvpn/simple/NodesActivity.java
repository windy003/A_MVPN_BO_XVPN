package com.myvpn.simple;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0019\u001a\u00020\u0013H\u0002J\b\u0010\u001a\u001a\u00020\u0013H\u0002J\b\u0010\u001b\u001a\u00020\u0013H\u0002J\b\u0010\u001c\u001a\u00020\u0013H\u0002J\u0012\u0010\u001d\u001a\u00020\u00132\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\u0010\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\u0018H\u0016J\u0010\u0010\"\u001a\u00020\u00132\u0006\u0010!\u001a\u00020\u0018H\u0016J\b\u0010#\u001a\u00020\u0013H\u0002J\u0016\u0010$\u001a\u00020\u00132\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00160&H\u0002J\b\u0010\'\u001a\u00020\u0013H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/myvpn/simple/NodesActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/myvpn/simple/NodeAdapter$OnNodeActionListener;", "()V", "adapter", "Lcom/myvpn/simple/NodeAdapter;", "btnAddFromClipboard", "Landroid/widget/Button;", "btnDeleteAll", "btnTestLatency", "isTesting", "", "nodeDao", "Lcom/myvpn/simple/database/TrojanNodeDao;", "rvNodes", "Landroidx/recyclerview/widget/RecyclerView;", "tvEmptyHint", "Landroid/widget/TextView;", "addFromClipboard", "", "addSingleNode", "config", "Lcom/myvpn/simple/TrojanConfig;", "createNodeWithLocation", "Lcom/myvpn/simple/database/TrojanNode;", "deleteAllNodes", "initDatabase", "initViews", "loadNodes", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onNodeDeleted", "node", "onNodeSelected", "setupRecyclerView", "showMultipleNodesDialog", "configs", "", "testAllNodesLatency", "app_debug"})
public final class NodesActivity extends androidx.appcompat.app.AppCompatActivity implements com.myvpn.simple.NodeAdapter.OnNodeActionListener {
    private androidx.recyclerview.widget.RecyclerView rvNodes;
    private android.widget.TextView tvEmptyHint;
    private android.widget.Button btnAddFromClipboard;
    private android.widget.Button btnTestLatency;
    private android.widget.Button btnDeleteAll;
    private com.myvpn.simple.NodeAdapter adapter;
    private com.myvpn.simple.database.TrojanNodeDao nodeDao;
    private boolean isTesting = false;
    
    public NodesActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initViews() {
    }
    
    private final void initDatabase() {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void loadNodes() {
    }
    
    private final void addFromClipboard() {
    }
    
    private final com.myvpn.simple.database.TrojanNode createNodeWithLocation(com.myvpn.simple.TrojanConfig config) {
        return null;
    }
    
    private final void addSingleNode(com.myvpn.simple.TrojanConfig config) {
    }
    
    private final void showMultipleNodesDialog(java.util.List<com.myvpn.simple.TrojanConfig> configs) {
    }
    
    @java.lang.Override
    public void onNodeSelected(@org.jetbrains.annotations.NotNull
    com.myvpn.simple.database.TrojanNode node) {
    }
    
    @java.lang.Override
    public void onNodeDeleted(@org.jetbrains.annotations.NotNull
    com.myvpn.simple.database.TrojanNode node) {
    }
    
    private final void testAllNodesLatency() {
    }
    
    private final void deleteAllNodes() {
    }
}