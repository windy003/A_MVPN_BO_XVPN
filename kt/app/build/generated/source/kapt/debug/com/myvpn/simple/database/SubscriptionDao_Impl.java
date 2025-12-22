package com.myvpn.simple.database;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SubscriptionDao_Impl implements SubscriptionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Subscription> __insertionAdapterOfSubscription;

  private final EntityDeletionOrUpdateAdapter<Subscription> __deletionAdapterOfSubscription;

  private final EntityDeletionOrUpdateAdapter<Subscription> __updateAdapterOfSubscription;

  private final SharedSQLiteStatement __preparedStmtOfDeleteSubscriptionById;

  private final SharedSQLiteStatement __preparedStmtOfUpdateNodeCount;

  public SubscriptionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSubscription = new EntityInsertionAdapter<Subscription>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `subscriptions` (`id`,`name`,`url`,`updateTime`,`nodeCount`,`createTime`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Subscription value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUrl());
        }
        stmt.bindLong(4, value.getUpdateTime());
        stmt.bindLong(5, value.getNodeCount());
        stmt.bindLong(6, value.getCreateTime());
      }
    };
    this.__deletionAdapterOfSubscription = new EntityDeletionOrUpdateAdapter<Subscription>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `subscriptions` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Subscription value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfSubscription = new EntityDeletionOrUpdateAdapter<Subscription>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `subscriptions` SET `id` = ?,`name` = ?,`url` = ?,`updateTime` = ?,`nodeCount` = ?,`createTime` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Subscription value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUrl());
        }
        stmt.bindLong(4, value.getUpdateTime());
        stmt.bindLong(5, value.getNodeCount());
        stmt.bindLong(6, value.getCreateTime());
        stmt.bindLong(7, value.getId());
      }
    };
    this.__preparedStmtOfDeleteSubscriptionById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM subscriptions WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateNodeCount = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE subscriptions SET nodeCount = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public long insertSubscription(final Subscription subscription) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfSubscription.insertAndReturnId(subscription);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteSubscription(final Subscription subscription) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfSubscription.handle(subscription);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateSubscription(final Subscription subscription) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfSubscription.handle(subscription);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteSubscriptionById(final long id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteSubscriptionById.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteSubscriptionById.release(_stmt);
    }
  }

  @Override
  public void updateNodeCount(final long subscriptionId, final int count) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateNodeCount.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, count);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, subscriptionId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateNodeCount.release(_stmt);
    }
  }

  @Override
  public List<Subscription> getAllSubscriptions() {
    final String _sql = "SELECT * FROM subscriptions ORDER BY updateTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
      final int _cursorIndexOfUpdateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "updateTime");
      final int _cursorIndexOfNodeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "nodeCount");
      final int _cursorIndexOfCreateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "createTime");
      final List<Subscription> _result = new ArrayList<Subscription>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Subscription _item;
        _item = new Subscription();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item.setName(_tmpName);
        final String _tmpUrl;
        if (_cursor.isNull(_cursorIndexOfUrl)) {
          _tmpUrl = null;
        } else {
          _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
        }
        _item.setUrl(_tmpUrl);
        final long _tmpUpdateTime;
        _tmpUpdateTime = _cursor.getLong(_cursorIndexOfUpdateTime);
        _item.setUpdateTime(_tmpUpdateTime);
        final int _tmpNodeCount;
        _tmpNodeCount = _cursor.getInt(_cursorIndexOfNodeCount);
        _item.setNodeCount(_tmpNodeCount);
        final long _tmpCreateTime;
        _tmpCreateTime = _cursor.getLong(_cursorIndexOfCreateTime);
        _item.setCreateTime(_tmpCreateTime);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Subscription getSubscriptionById(final long id) {
    final String _sql = "SELECT * FROM subscriptions WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
      final int _cursorIndexOfUpdateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "updateTime");
      final int _cursorIndexOfNodeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "nodeCount");
      final int _cursorIndexOfCreateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "createTime");
      final Subscription _result;
      if(_cursor.moveToFirst()) {
        _result = new Subscription();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _result.setName(_tmpName);
        final String _tmpUrl;
        if (_cursor.isNull(_cursorIndexOfUrl)) {
          _tmpUrl = null;
        } else {
          _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
        }
        _result.setUrl(_tmpUrl);
        final long _tmpUpdateTime;
        _tmpUpdateTime = _cursor.getLong(_cursorIndexOfUpdateTime);
        _result.setUpdateTime(_tmpUpdateTime);
        final int _tmpNodeCount;
        _tmpNodeCount = _cursor.getInt(_cursorIndexOfNodeCount);
        _result.setNodeCount(_tmpNodeCount);
        final long _tmpCreateTime;
        _tmpCreateTime = _cursor.getLong(_cursorIndexOfCreateTime);
        _result.setCreateTime(_tmpCreateTime);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getSubscriptionCount() {
    final String _sql = "SELECT COUNT(*) FROM subscriptions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
