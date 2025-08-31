package com.myvpn.simple.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myvpn.simple.R;
import com.myvpn.simple.SimpleVPNService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppExclusionActivity extends AppCompatActivity implements AppExclusionAdapter.OnAppExclusionChangeListener {
    
    private Spinner spinnerMode;
    private TextView textModeDescription;
    private EditText editSearch;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    
    private AppExclusionAdapter adapter;
    private AppExclusionManager appExclusionManager;
    private ExecutorService executorService;
    private List<AppInfo> allApps = new ArrayList<>();
    
    private SimpleVPNService vpnService;
    private boolean isServiceBound = false;
    
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            SimpleVPNService.VPNBinder binder = (SimpleVPNService.VPNBinder) service;
            vpnService = binder.getService();
            appExclusionManager = binder.getAppExclusionManager();
            isServiceBound = true;
            
            setupUI();
        }
        
        @Override
        public void onServiceDisconnected(ComponentName name) {
            vpnService = null;
            appExclusionManager = null;
            isServiceBound = false;
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_exclusion);
        
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("应用排除设置");
        }
        
        initViews();
        executorService = Executors.newSingleThreadExecutor();
        
        // 绑定服务
        Intent serviceIntent = new Intent(this, SimpleVPNService.class);
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
    
    private void initViews() {
        spinnerMode = findViewById(R.id.spinner_exclusion_mode);
        textModeDescription = findViewById(R.id.text_mode_description);
        editSearch = findViewById(R.id.edit_search);
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view);
        
        // 设置RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AppExclusionAdapter();
        adapter.setOnAppExclusionChangeListener(this);
        recyclerView.setAdapter(adapter);
        
        // 设置搜索框
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.setSearchQuery(s.toString());
            }
            
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
    
    private void setupUI() {
        if (appExclusionManager == null) {
            return;
        }
        
        // 设置模式选择器
        String[] modes = {"排除模式 (默认所有走代理)", "允许模式 (默认所有不走代理)"};
        ArrayAdapter<String> modeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, modes);
        modeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMode.setAdapter(modeAdapter);
        
        // 设置当前模式
        AppExclusionManager.ExclusionMode currentMode = appExclusionManager.getExclusionMode();
        spinnerMode.setSelection(currentMode == AppExclusionManager.ExclusionMode.BLACKLIST ? 0 : 1);
        updateModeDescription(currentMode);
        
        spinnerMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AppExclusionManager.ExclusionMode mode = position == 0 ? 
                    AppExclusionManager.ExclusionMode.BLACKLIST : 
                    AppExclusionManager.ExclusionMode.WHITELIST;
                appExclusionManager.setExclusionMode(mode);
                updateModeDescription(mode);
            }
            
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        
        // 加载应用列表
        loadAppList();
    }
    
    private void updateModeDescription(AppExclusionManager.ExclusionMode mode) {
        if (mode == AppExclusionManager.ExclusionMode.BLACKLIST) {
            textModeDescription.setText("排除模式：默认情况下所有应用都会通过VPN连接，选中的应用将不走代理");
        } else {
            textModeDescription.setText("允许模式：默认情况下所有应用都不会通过VPN连接，只有选中的应用才走代理");
        }
    }
    
    private void loadAppList() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        
        executorService.execute(() -> {
            List<AppInfo> apps = new ArrayList<>();
            PackageManager pm = getPackageManager();
            Set<String> excludedApps = appExclusionManager.getExcludedApps();
            
            try {
                List<PackageInfo> packages = pm.getInstalledPackages(0);
                for (PackageInfo packageInfo : packages) {
                    ApplicationInfo appInfo = packageInfo.applicationInfo;
                    
                    // 只跳过本应用
                    if (appInfo.packageName.equals(getPackageName())) {
                        continue;
                    }
                    
                    String appName = appInfo.loadLabel(pm).toString();
                    boolean isExcluded = excludedApps.contains(appInfo.packageName);
                    boolean isSystemApp = (appInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
                    
                    apps.add(new AppInfo(appName, appInfo.packageName, appInfo.loadIcon(pm), isSystemApp, isExcluded));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            allApps = apps;
            
            runOnUiThread(() -> {
                adapter.setAppList(allApps);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            });
        });
    }
    
    @Override
    public void onAppExclusionChanged(String packageName, boolean isExcluded) {
        if (appExclusionManager != null) {
            if (isExcluded) {
                appExclusionManager.addExcludedApp(packageName);
            } else {
                appExclusionManager.removeExcludedApp(packageName);
            }
        }
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
        if (isServiceBound) {
            unbindService(serviceConnection);
            isServiceBound = false;
        }
    }
}