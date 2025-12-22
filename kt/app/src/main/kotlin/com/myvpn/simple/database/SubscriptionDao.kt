package com.myvpn.simple.database

import androidx.room.*

@Dao
interface SubscriptionDao {

    @Query("SELECT * FROM subscriptions ORDER BY updateTime DESC")
    fun getAllSubscriptions(): List<Subscription>

    @Query("SELECT * FROM subscriptions WHERE id = :id")
    fun getSubscriptionById(id: Long): Subscription?

    @Insert
    fun insertSubscription(subscription: Subscription): Long

    @Update
    fun updateSubscription(subscription: Subscription)

    @Delete
    fun deleteSubscription(subscription: Subscription)

    @Query("DELETE FROM subscriptions WHERE id = :id")
    fun deleteSubscriptionById(id: Long)

    @Query("SELECT COUNT(*) FROM subscriptions")
    fun getSubscriptionCount(): Int

    @Query("UPDATE subscriptions SET nodeCount = :count WHERE id = :subscriptionId")
    fun updateNodeCount(subscriptionId: Long, count: Int)
}
