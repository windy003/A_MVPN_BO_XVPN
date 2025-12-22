package com.myvpn.simple.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\'J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nH\'J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH\'J\b\u0010\f\u001a\u00020\rH\'J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\rH\'J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u0013"}, d2 = {"Lcom/myvpn/simple/database/SubscriptionDao;", "", "deleteSubscription", "", "subscription", "Lcom/myvpn/simple/database/Subscription;", "deleteSubscriptionById", "id", "", "getAllSubscriptions", "", "getSubscriptionById", "getSubscriptionCount", "", "insertSubscription", "updateNodeCount", "subscriptionId", "count", "updateSubscription", "app_debug"})
@androidx.room.Dao
public abstract interface SubscriptionDao {
    
    @androidx.room.Query(value = "SELECT * FROM subscriptions ORDER BY updateTime DESC")
    @org.jetbrains.annotations.NotNull
    public abstract java.util.List<com.myvpn.simple.database.Subscription> getAllSubscriptions();
    
    @androidx.room.Query(value = "SELECT * FROM subscriptions WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract com.myvpn.simple.database.Subscription getSubscriptionById(long id);
    
    @androidx.room.Insert
    public abstract long insertSubscription(@org.jetbrains.annotations.NotNull
    com.myvpn.simple.database.Subscription subscription);
    
    @androidx.room.Update
    public abstract void updateSubscription(@org.jetbrains.annotations.NotNull
    com.myvpn.simple.database.Subscription subscription);
    
    @androidx.room.Delete
    public abstract void deleteSubscription(@org.jetbrains.annotations.NotNull
    com.myvpn.simple.database.Subscription subscription);
    
    @androidx.room.Query(value = "DELETE FROM subscriptions WHERE id = :id")
    public abstract void deleteSubscriptionById(long id);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM subscriptions")
    public abstract int getSubscriptionCount();
    
    @androidx.room.Query(value = "UPDATE subscriptions SET nodeCount = :count WHERE id = :subscriptionId")
    public abstract void updateNodeCount(long subscriptionId, int count);
}