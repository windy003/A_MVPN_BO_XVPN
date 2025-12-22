package com.myvpn.simple.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001eH\u0002J\u0018\u0010 \u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0010H\u0016J\u0012\u0010$\u001a\u00020\u001e2\b\u0010%\u001a\u0004\u0018\u00010&H\u0014J\b\u0010\'\u001a\u00020\u001eH\u0014J\u0010\u0010(\u001a\u00020\u00102\u0006\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020\u001eH\u0002J\u0010\u0010,\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020.H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lcom/myvpn/simple/ui/AppExclusionActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/myvpn/simple/ui/AppExclusionAdapter$OnAppExclusionChangeListener;", "()V", "adapter", "Lcom/myvpn/simple/ui/AppExclusionAdapter;", "allApps", "", "Lcom/myvpn/simple/ui/AppInfo;", "appExclusionManager", "Lcom/myvpn/simple/ui/AppExclusionManager;", "editSearch", "Landroid/widget/EditText;", "executorService", "Ljava/util/concurrent/ExecutorService;", "isServiceBound", "", "progressBar", "Landroid/widget/ProgressBar;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "serviceConnection", "Landroid/content/ServiceConnection;", "spinnerMode", "Landroid/widget/Spinner;", "textModeDescription", "Landroid/widget/TextView;", "vpnService", "Lcom/myvpn/simple/SimpleVPNService;", "initViews", "", "loadAppList", "onAppExclusionChanged", "packageName", "", "isExcluded", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "setupUI", "updateModeDescription", "mode", "Lcom/myvpn/simple/ui/AppExclusionManager$ExclusionMode;", "app_debug"})
public final class AppExclusionActivity extends androidx.appcompat.app.AppCompatActivity implements com.myvpn.simple.ui.AppExclusionAdapter.OnAppExclusionChangeListener {
    private android.widget.Spinner spinnerMode;
    private android.widget.TextView textModeDescription;
    private android.widget.EditText editSearch;
    private android.widget.ProgressBar progressBar;
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private com.myvpn.simple.ui.AppExclusionAdapter adapter;
    @org.jetbrains.annotations.Nullable
    private com.myvpn.simple.ui.AppExclusionManager appExclusionManager;
    private java.util.concurrent.ExecutorService executorService;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.myvpn.simple.ui.AppInfo> allApps;
    @org.jetbrains.annotations.Nullable
    private com.myvpn.simple.SimpleVPNService vpnService;
    private boolean isServiceBound = false;
    @org.jetbrains.annotations.NotNull
    private final android.content.ServiceConnection serviceConnection = null;
    
    public AppExclusionActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initViews() {
    }
    
    private final void setupUI() {
    }
    
    private final void updateModeDescription(com.myvpn.simple.ui.AppExclusionManager.ExclusionMode mode) {
    }
    
    private final void loadAppList() {
    }
    
    @java.lang.Override
    public void onAppExclusionChanged(@org.jetbrains.annotations.NotNull
    java.lang.String packageName, boolean isExcluded) {
    }
    
    @java.lang.Override
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
}