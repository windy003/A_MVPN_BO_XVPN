package com.myvpn.simple.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u001c\u001dB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000fH\u0016J\u0014\u0010\u0017\u001a\u00020\r2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0018J\u000e\u0010\u0019\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u000bR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/myvpn/simple/ui/AppExclusionAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/myvpn/simple/ui/AppExclusionAdapter$ViewHolder;", "()V", "appList", "", "Lcom/myvpn/simple/ui/AppInfo;", "filteredAppList", "listener", "Lcom/myvpn/simple/ui/AppExclusionAdapter$OnAppExclusionChangeListener;", "searchQuery", "", "applyFilter", "", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setAppList", "", "setOnAppExclusionChangeListener", "setSearchQuery", "query", "OnAppExclusionChangeListener", "ViewHolder", "app_debug"})
public final class AppExclusionAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.myvpn.simple.ui.AppExclusionAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.myvpn.simple.ui.AppInfo> appList;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.myvpn.simple.ui.AppInfo> filteredAppList;
    @org.jetbrains.annotations.Nullable
    private com.myvpn.simple.ui.AppExclusionAdapter.OnAppExclusionChangeListener listener;
    @org.jetbrains.annotations.NotNull
    private java.lang.String searchQuery = "";
    
    public AppExclusionAdapter() {
        super();
    }
    
    public final void setOnAppExclusionChangeListener(@org.jetbrains.annotations.NotNull
    com.myvpn.simple.ui.AppExclusionAdapter.OnAppExclusionChangeListener listener) {
    }
    
    public final void setAppList(@org.jetbrains.annotations.NotNull
    java.util.List<com.myvpn.simple.ui.AppInfo> appList) {
    }
    
    public final void setSearchQuery(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
    }
    
    private final void applyFilter() {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.myvpn.simple.ui.AppExclusionAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.myvpn.simple.ui.AppExclusionAdapter.ViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u00e6\u0080\u0001\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/myvpn/simple/ui/AppExclusionAdapter$OnAppExclusionChangeListener;", "", "onAppExclusionChanged", "", "packageName", "", "isExcluded", "", "app_debug"})
    public static abstract interface OnAppExclusionChangeListener {
        
        public abstract void onAppExclusionChanged(@org.jetbrains.annotations.NotNull
        java.lang.String packageName, boolean isExcluded);
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\f\u00a8\u0006\u0015"}, d2 = {"Lcom/myvpn/simple/ui/AppExclusionAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "appIcon", "Landroid/widget/ImageView;", "getAppIcon", "()Landroid/widget/ImageView;", "appName", "Landroid/widget/TextView;", "getAppName", "()Landroid/widget/TextView;", "appType", "getAppType", "checkBox", "Landroid/widget/CheckBox;", "getCheckBox", "()Landroid/widget/CheckBox;", "packageName", "getPackageName", "app_debug"})
    public static final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView appIcon = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView appName = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView packageName = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView appType = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.CheckBox checkBox = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageView getAppIcon() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getAppName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getPackageName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getAppType() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.CheckBox getCheckBox() {
            return null;
        }
    }
}