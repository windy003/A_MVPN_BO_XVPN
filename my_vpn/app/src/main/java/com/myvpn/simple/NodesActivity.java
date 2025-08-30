package com.myvpn.simple;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.myvpn.simple.database.NodeDatabase;
import com.myvpn.simple.database.TrojanNode;
import com.myvpn.simple.database.TrojanNodeDao;
import java.util.List;

public class NodesActivity extends AppCompatActivity implements NodeAdapter.OnNodeActionListener {
    
    private RecyclerView rvNodes;
    private TextView tvEmptyHint;
    private Button btnAddFromClipboard;
    private Button btnPasteSubscription;
    private NodeAdapter adapter;
    private TrojanNodeDao nodeDao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodes);
        
        initViews();
        initDatabase();
        setupRecyclerView();
        loadNodes();
    }
    
    private void initViews() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        
        rvNodes = findViewById(R.id.rv_nodes);
        tvEmptyHint = findViewById(R.id.tv_empty_hint);
        btnAddFromClipboard = findViewById(R.id.btn_add_from_clipboard);
        btnPasteSubscription = findViewById(R.id.btn_paste_subscription);
        
        btnAddFromClipboard.setOnClickListener(v -> addFromClipboard());
        btnPasteSubscription.setOnClickListener(v -> importSubscription());
    }
    
    private void initDatabase() {
        NodeDatabase database = NodeDatabase.getInstance(this);
        nodeDao = database.trojanNodeDao();
    }
    
    private void setupRecyclerView() {
        adapter = new NodeAdapter(this);
        rvNodes.setLayoutManager(new LinearLayoutManager(this));
        rvNodes.setAdapter(adapter);
    }
    
    private void loadNodes() {
        new Thread(() -> {
            List<TrojanNode> nodes = nodeDao.getAllNodes();
            runOnUiThread(() -> {
                adapter.setNodes(nodes);
                tvEmptyHint.setVisibility(nodes.isEmpty() ? View.VISIBLE : View.GONE);
            });
        }).start();
    }
    
    private void addFromClipboard() {
        List<TrojanConfig> configs = ClipboardHelper.readSubscriptionFromClipboard(this);
        
        if (configs.isEmpty()) {
            Toast.makeText(this, "剪贴板中没有找到有效的Trojan链接", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (configs.size() == 1) {
            addSingleNode(configs.get(0));
        } else {
            showMultipleNodesDialog(configs);
        }
    }
    
    private void addSingleNode(TrojanConfig config) {
        new Thread(() -> {
            TrojanNode node = TrojanNode.fromConfig(config);
            long nodeId = nodeDao.insertNode(node);
            
            runOnUiThread(() -> {
                Toast.makeText(this, "节点添加成功", Toast.LENGTH_SHORT).show();
                loadNodes();
            });
        }).start();
    }
    
    private void showMultipleNodesDialog(List<TrojanConfig> configs) {
        new AlertDialog.Builder(this)
            .setTitle("发现多个节点")
            .setMessage("从剪贴板中发现 " + configs.size() + " 个节点，是否全部添加？")
            .setPositiveButton("全部添加", (dialog, which) -> {
                new Thread(() -> {
                    int addedCount = 0;
                    for (TrojanConfig config : configs) {
                        TrojanNode node = TrojanNode.fromConfig(config);
                        nodeDao.insertNode(node);
                        addedCount++;
                    }
                    
                    final int finalCount = addedCount;
                    runOnUiThread(() -> {
                        Toast.makeText(this, "成功添加 " + finalCount + " 个节点", Toast.LENGTH_SHORT).show();
                        loadNodes();
                    });
                }).start();
            })
            .setNeutralButton("添加第一个", (dialog, which) -> addSingleNode(configs.get(0)))
            .setNegativeButton("取消", null)
            .show();
    }
    
    private void importSubscription() {
        List<TrojanConfig> configs = ClipboardHelper.readSubscriptionFromClipboard(this);
        
        if (configs.isEmpty()) {
            Toast.makeText(this, "剪贴板中没有找到有效的订阅链接", Toast.LENGTH_SHORT).show();
            return;
        }
        
        new AlertDialog.Builder(this)
            .setTitle("导入订阅")
            .setMessage("发现 " + configs.size() + " 个节点，是否导入？")
            .setPositiveButton("导入", (dialog, which) -> {
                new Thread(() -> {
                    int addedCount = 0;
                    for (TrojanConfig config : configs) {
                        TrojanNode node = TrojanNode.fromConfig(config);
                        nodeDao.insertNode(node);
                        addedCount++;
                    }
                    
                    final int finalCount = addedCount;
                    runOnUiThread(() -> {
                        Toast.makeText(this, "成功导入 " + finalCount + " 个节点", Toast.LENGTH_SHORT).show();
                        loadNodes();
                    });
                }).start();
            })
            .setNegativeButton("取消", null)
            .show();
    }
    
    @Override
    public void onNodeSelected(TrojanNode node) {
        new Thread(() -> {
            nodeDao.clearAllSelections();
            nodeDao.selectNode(node.id);
            
            runOnUiThread(() -> {
                Toast.makeText(this, "已选择节点: " + node.getDisplayName(), Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
            });
        }).start();
    }
    
    @Override
    public void onNodeDeleted(TrojanNode node) {
        new AlertDialog.Builder(this)
            .setTitle("删除节点")
            .setMessage("确定要删除节点 \"" + node.getDisplayName() + "\" 吗？")
            .setPositiveButton("删除", (dialog, which) -> {
                new Thread(() -> {
                    nodeDao.deleteNode(node);
                    runOnUiThread(() -> {
                        Toast.makeText(this, "节点已删除", Toast.LENGTH_SHORT).show();
                        loadNodes();
                    });
                }).start();
            })
            .setNegativeButton("取消", null)
            .show();
    }
}