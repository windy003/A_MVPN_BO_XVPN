package com.myvpn.simple.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [TrojanNode::class, Subscription::class], version = 4, exportSchema = false)
abstract class NodeDatabase : RoomDatabase() {

    abstract fun trojanNodeDao(): TrojanNodeDao
    abstract fun subscriptionDao(): SubscriptionDao

    companion object {
        @Volatile
        private var INSTANCE: NodeDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 添加 country 列
                database.execSQL("ALTER TABLE trojan_nodes ADD COLUMN country TEXT NOT NULL DEFAULT ''")
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 添加 latency 列
                database.execSQL("ALTER TABLE trojan_nodes ADD COLUMN latency INTEGER NOT NULL DEFAULT -1")
            }
        }

        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 创建 subscriptions 表
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS subscriptions (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        name TEXT NOT NULL,
                        url TEXT NOT NULL,
                        updateTime INTEGER NOT NULL,
                        nodeCount INTEGER NOT NULL,
                        createTime INTEGER NOT NULL
                    )
                """)

                // 添加 subscriptionId 列到 trojan_nodes 表
                database.execSQL("ALTER TABLE trojan_nodes ADD COLUMN subscriptionId INTEGER NOT NULL DEFAULT 0")
            }
        }

        fun getInstance(context: Context): NodeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NodeDatabase::class.java,
                    "node_database"
                )
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
