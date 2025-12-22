package com.myvpn.simple.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class NodeDatabase_Impl extends NodeDatabase {
  private volatile TrojanNodeDao _trojanNodeDao;

  private volatile SubscriptionDao _subscriptionDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(4) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `trojan_nodes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `server` TEXT NOT NULL, `port` INTEGER NOT NULL, `password` TEXT NOT NULL, `sni` TEXT NOT NULL, `allowInsecure` INTEGER NOT NULL, `country` TEXT NOT NULL, `latency` INTEGER NOT NULL, `subscriptionId` INTEGER NOT NULL, `createTime` INTEGER NOT NULL, `isSelected` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `subscriptions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `url` TEXT NOT NULL, `updateTime` INTEGER NOT NULL, `nodeCount` INTEGER NOT NULL, `createTime` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '874d42413bd4e8f6181f13fbbb9bb954')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `trojan_nodes`");
        _db.execSQL("DROP TABLE IF EXISTS `subscriptions`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTrojanNodes = new HashMap<String, TableInfo.Column>(12);
        _columnsTrojanNodes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrojanNodes.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrojanNodes.put("server", new TableInfo.Column("server", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrojanNodes.put("port", new TableInfo.Column("port", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrojanNodes.put("password", new TableInfo.Column("password", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrojanNodes.put("sni", new TableInfo.Column("sni", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrojanNodes.put("allowInsecure", new TableInfo.Column("allowInsecure", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrojanNodes.put("country", new TableInfo.Column("country", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrojanNodes.put("latency", new TableInfo.Column("latency", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrojanNodes.put("subscriptionId", new TableInfo.Column("subscriptionId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrojanNodes.put("createTime", new TableInfo.Column("createTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrojanNodes.put("isSelected", new TableInfo.Column("isSelected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTrojanNodes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTrojanNodes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTrojanNodes = new TableInfo("trojan_nodes", _columnsTrojanNodes, _foreignKeysTrojanNodes, _indicesTrojanNodes);
        final TableInfo _existingTrojanNodes = TableInfo.read(_db, "trojan_nodes");
        if (! _infoTrojanNodes.equals(_existingTrojanNodes)) {
          return new RoomOpenHelper.ValidationResult(false, "trojan_nodes(com.myvpn.simple.database.TrojanNode).\n"
                  + " Expected:\n" + _infoTrojanNodes + "\n"
                  + " Found:\n" + _existingTrojanNodes);
        }
        final HashMap<String, TableInfo.Column> _columnsSubscriptions = new HashMap<String, TableInfo.Column>(6);
        _columnsSubscriptions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubscriptions.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubscriptions.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubscriptions.put("updateTime", new TableInfo.Column("updateTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubscriptions.put("nodeCount", new TableInfo.Column("nodeCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubscriptions.put("createTime", new TableInfo.Column("createTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSubscriptions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSubscriptions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSubscriptions = new TableInfo("subscriptions", _columnsSubscriptions, _foreignKeysSubscriptions, _indicesSubscriptions);
        final TableInfo _existingSubscriptions = TableInfo.read(_db, "subscriptions");
        if (! _infoSubscriptions.equals(_existingSubscriptions)) {
          return new RoomOpenHelper.ValidationResult(false, "subscriptions(com.myvpn.simple.database.Subscription).\n"
                  + " Expected:\n" + _infoSubscriptions + "\n"
                  + " Found:\n" + _existingSubscriptions);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "874d42413bd4e8f6181f13fbbb9bb954", "3a6d91086bddfd4e5433c213bf680150");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "trojan_nodes","subscriptions");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `trojan_nodes`");
      _db.execSQL("DELETE FROM `subscriptions`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(TrojanNodeDao.class, TrojanNodeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SubscriptionDao.class, SubscriptionDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public TrojanNodeDao trojanNodeDao() {
    if (_trojanNodeDao != null) {
      return _trojanNodeDao;
    } else {
      synchronized(this) {
        if(_trojanNodeDao == null) {
          _trojanNodeDao = new TrojanNodeDao_Impl(this);
        }
        return _trojanNodeDao;
      }
    }
  }

  @Override
  public SubscriptionDao subscriptionDao() {
    if (_subscriptionDao != null) {
      return _subscriptionDao;
    } else {
      synchronized(this) {
        if(_subscriptionDao == null) {
          _subscriptionDao = new SubscriptionDao_Impl(this);
        }
        return _subscriptionDao;
      }
    }
  }
}
