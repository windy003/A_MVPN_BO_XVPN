package com.myvpn.simple.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {TrojanNode.class}, version = 1, exportSchema = false)
public abstract class NodeDatabase extends RoomDatabase {
    
    private static NodeDatabase INSTANCE;
    
    public abstract TrojanNodeDao trojanNodeDao();
    
    public static synchronized NodeDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                context.getApplicationContext(),
                NodeDatabase.class,
                "node_database"
            ).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}