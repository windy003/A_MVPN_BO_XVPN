package com.myvpn.simple;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002\u0014\u0015B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016J\u001c\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\nH\u0016J\u001c\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0016J\u0014\u0010\u0013\u001a\u00020\f2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/myvpn/simple/SubscriptionAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/myvpn/simple/SubscriptionAdapter$SubscriptionViewHolder;", "listener", "Lcom/myvpn/simple/SubscriptionAdapter$OnSubscriptionActionListener;", "(Lcom/myvpn/simple/SubscriptionAdapter$OnSubscriptionActionListener;)V", "subscriptions", "", "Lcom/myvpn/simple/database/Subscription;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setSubscriptions", "OnSubscriptionActionListener", "SubscriptionViewHolder", "app_debug"})
public final class SubscriptionAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.myvpn.simple.SubscriptionAdapter.SubscriptionViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final com.myvpn.simple.SubscriptionAdapter.OnSubscriptionActionListener listener = null;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.myvpn.simple.database.Subscription> subscriptions;
    
    public SubscriptionAdapter(@org.jetbrains.annotations.NotNull
    com.myvpn.simple.SubscriptionAdapter.OnSubscriptionActionListener listener) {
        super();
    }
    
    public final void setSubscriptions(@org.jetbrains.annotations.NotNull
    java.util.List<com.myvpn.simple.database.Subscription> subscriptions) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.myvpn.simple.SubscriptionAdapter.SubscriptionViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.myvpn.simple.SubscriptionAdapter.SubscriptionViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\t"}, d2 = {"Lcom/myvpn/simple/SubscriptionAdapter$OnSubscriptionActionListener;", "", "onSubscriptionClick", "", "subscription", "Lcom/myvpn/simple/database/Subscription;", "onSubscriptionDelete", "onSubscriptionRename", "onSubscriptionUpdate", "app_debug"})
    public static abstract interface OnSubscriptionActionListener {
        
        public abstract void onSubscriptionClick(@org.jetbrains.annotations.NotNull
        com.myvpn.simple.database.Subscription subscription);
        
        public abstract void onSubscriptionRename(@org.jetbrains.annotations.NotNull
        com.myvpn.simple.database.Subscription subscription);
        
        public abstract void onSubscriptionUpdate(@org.jetbrains.annotations.NotNull
        com.myvpn.simple.database.Subscription subscription);
        
        public abstract void onSubscriptionDelete(@org.jetbrains.annotations.NotNull
        com.myvpn.simple.database.Subscription subscription);
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/myvpn/simple/SubscriptionAdapter$SubscriptionViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/myvpn/simple/SubscriptionAdapter;Landroid/view/View;)V", "btnDelete", "Landroid/widget/ImageButton;", "btnRename", "btnUpdate", "tvName", "Landroid/widget/TextView;", "tvNodeCount", "tvUpdateTime", "bind", "", "subscription", "Lcom/myvpn/simple/database/Subscription;", "app_debug"})
    public final class SubscriptionViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvName = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvNodeCount = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvUpdateTime = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageButton btnRename = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageButton btnUpdate = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageButton btnDelete = null;
        
        public SubscriptionViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.myvpn.simple.database.Subscription subscription) {
        }
    }
}