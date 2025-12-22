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
public final class TrojanNodeDao_Impl implements TrojanNodeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TrojanNode> __insertionAdapterOfTrojanNode;

  private final EntityDeletionOrUpdateAdapter<TrojanNode> __deletionAdapterOfTrojanNode;

  private final EntityDeletionOrUpdateAdapter<TrojanNode> __updateAdapterOfTrojanNode;

  private final SharedSQLiteStatement __preparedStmtOfClearAllSelections;

  private final SharedSQLiteStatement __preparedStmtOfSelectNode;

  private final SharedSQLiteStatement __preparedStmtOfDeleteNodeById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteNodesBySubscription;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllNodes;

  public TrojanNodeDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTrojanNode = new EntityInsertionAdapter<TrojanNode>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `trojan_nodes` (`id`,`name`,`server`,`port`,`password`,`sni`,`allowInsecure`,`country`,`latency`,`subscriptionId`,`createTime`,`isSelected`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TrojanNode value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getServer() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getServer());
        }
        stmt.bindLong(4, value.getPort());
        if (value.getPassword() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPassword());
        }
        if (value.getSni() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getSni());
        }
        final int _tmp = value.getAllowInsecure() ? 1 : 0;
        stmt.bindLong(7, _tmp);
        if (value.getCountry() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getCountry());
        }
        stmt.bindLong(9, value.getLatency());
        stmt.bindLong(10, value.getSubscriptionId());
        stmt.bindLong(11, value.getCreateTime());
        final int _tmp_1 = value.isSelected() ? 1 : 0;
        stmt.bindLong(12, _tmp_1);
      }
    };
    this.__deletionAdapterOfTrojanNode = new EntityDeletionOrUpdateAdapter<TrojanNode>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `trojan_nodes` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TrojanNode value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfTrojanNode = new EntityDeletionOrUpdateAdapter<TrojanNode>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `trojan_nodes` SET `id` = ?,`name` = ?,`server` = ?,`port` = ?,`password` = ?,`sni` = ?,`allowInsecure` = ?,`country` = ?,`latency` = ?,`subscriptionId` = ?,`createTime` = ?,`isSelected` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TrojanNode value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getServer() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getServer());
        }
        stmt.bindLong(4, value.getPort());
        if (value.getPassword() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPassword());
        }
        if (value.getSni() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getSni());
        }
        final int _tmp = value.getAllowInsecure() ? 1 : 0;
        stmt.bindLong(7, _tmp);
        if (value.getCountry() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getCountry());
        }
        stmt.bindLong(9, value.getLatency());
        stmt.bindLong(10, value.getSubscriptionId());
        stmt.bindLong(11, value.getCreateTime());
        final int _tmp_1 = value.isSelected() ? 1 : 0;
        stmt.bindLong(12, _tmp_1);
        stmt.bindLong(13, value.getId());
      }
    };
    this.__preparedStmtOfClearAllSelections = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE trojan_nodes SET isSelected = 0";
        return _query;
      }
    };
    this.__preparedStmtOfSelectNode = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE trojan_nodes SET isSelected = 1 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteNodeById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM trojan_nodes WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteNodesBySubscription = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM trojan_nodes WHERE subscriptionId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllNodes = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM trojan_nodes";
        return _query;
      }
    };
  }

  @Override
  public long insertNode(final TrojanNode node) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfTrojanNode.insertAndReturnId(node);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteNode(final TrojanNode node) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfTrojanNode.handle(node);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateNode(final TrojanNode node) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTrojanNode.handle(node);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void clearAllSelections() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClearAllSelections.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfClearAllSelections.release(_stmt);
    }
  }

  @Override
  public void selectNode(final long nodeId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSelectNode.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, nodeId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSelectNode.release(_stmt);
    }
  }

  @Override
  public void deleteNodeById(final long nodeId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteNodeById.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, nodeId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteNodeById.release(_stmt);
    }
  }

  @Override
  public void deleteNodesBySubscription(final long subscriptionId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteNodesBySubscription.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, subscriptionId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteNodesBySubscription.release(_stmt);
    }
  }

  @Override
  public void deleteAllNodes() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllNodes.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllNodes.release(_stmt);
    }
  }

  @Override
  public List<TrojanNode> getAllNodes() {
    final String _sql = "SELECT * FROM trojan_nodes ORDER BY createTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfServer = CursorUtil.getColumnIndexOrThrow(_cursor, "server");
      final int _cursorIndexOfPort = CursorUtil.getColumnIndexOrThrow(_cursor, "port");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfSni = CursorUtil.getColumnIndexOrThrow(_cursor, "sni");
      final int _cursorIndexOfAllowInsecure = CursorUtil.getColumnIndexOrThrow(_cursor, "allowInsecure");
      final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
      final int _cursorIndexOfLatency = CursorUtil.getColumnIndexOrThrow(_cursor, "latency");
      final int _cursorIndexOfSubscriptionId = CursorUtil.getColumnIndexOrThrow(_cursor, "subscriptionId");
      final int _cursorIndexOfCreateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "createTime");
      final int _cursorIndexOfIsSelected = CursorUtil.getColumnIndexOrThrow(_cursor, "isSelected");
      final List<TrojanNode> _result = new ArrayList<TrojanNode>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TrojanNode _item;
        _item = new TrojanNode();
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
        final String _tmpServer;
        if (_cursor.isNull(_cursorIndexOfServer)) {
          _tmpServer = null;
        } else {
          _tmpServer = _cursor.getString(_cursorIndexOfServer);
        }
        _item.setServer(_tmpServer);
        final int _tmpPort;
        _tmpPort = _cursor.getInt(_cursorIndexOfPort);
        _item.setPort(_tmpPort);
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _item.setPassword(_tmpPassword);
        final String _tmpSni;
        if (_cursor.isNull(_cursorIndexOfSni)) {
          _tmpSni = null;
        } else {
          _tmpSni = _cursor.getString(_cursorIndexOfSni);
        }
        _item.setSni(_tmpSni);
        final boolean _tmpAllowInsecure;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfAllowInsecure);
        _tmpAllowInsecure = _tmp != 0;
        _item.setAllowInsecure(_tmpAllowInsecure);
        final String _tmpCountry;
        if (_cursor.isNull(_cursorIndexOfCountry)) {
          _tmpCountry = null;
        } else {
          _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
        }
        _item.setCountry(_tmpCountry);
        final int _tmpLatency;
        _tmpLatency = _cursor.getInt(_cursorIndexOfLatency);
        _item.setLatency(_tmpLatency);
        final long _tmpSubscriptionId;
        _tmpSubscriptionId = _cursor.getLong(_cursorIndexOfSubscriptionId);
        _item.setSubscriptionId(_tmpSubscriptionId);
        final long _tmpCreateTime;
        _tmpCreateTime = _cursor.getLong(_cursorIndexOfCreateTime);
        _item.setCreateTime(_tmpCreateTime);
        final boolean _tmpIsSelected;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsSelected);
        _tmpIsSelected = _tmp_1 != 0;
        _item.setSelected(_tmpIsSelected);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public TrojanNode getSelectedNode() {
    final String _sql = "SELECT * FROM trojan_nodes WHERE isSelected = 1 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfServer = CursorUtil.getColumnIndexOrThrow(_cursor, "server");
      final int _cursorIndexOfPort = CursorUtil.getColumnIndexOrThrow(_cursor, "port");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfSni = CursorUtil.getColumnIndexOrThrow(_cursor, "sni");
      final int _cursorIndexOfAllowInsecure = CursorUtil.getColumnIndexOrThrow(_cursor, "allowInsecure");
      final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
      final int _cursorIndexOfLatency = CursorUtil.getColumnIndexOrThrow(_cursor, "latency");
      final int _cursorIndexOfSubscriptionId = CursorUtil.getColumnIndexOrThrow(_cursor, "subscriptionId");
      final int _cursorIndexOfCreateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "createTime");
      final int _cursorIndexOfIsSelected = CursorUtil.getColumnIndexOrThrow(_cursor, "isSelected");
      final TrojanNode _result;
      if(_cursor.moveToFirst()) {
        _result = new TrojanNode();
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
        final String _tmpServer;
        if (_cursor.isNull(_cursorIndexOfServer)) {
          _tmpServer = null;
        } else {
          _tmpServer = _cursor.getString(_cursorIndexOfServer);
        }
        _result.setServer(_tmpServer);
        final int _tmpPort;
        _tmpPort = _cursor.getInt(_cursorIndexOfPort);
        _result.setPort(_tmpPort);
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _result.setPassword(_tmpPassword);
        final String _tmpSni;
        if (_cursor.isNull(_cursorIndexOfSni)) {
          _tmpSni = null;
        } else {
          _tmpSni = _cursor.getString(_cursorIndexOfSni);
        }
        _result.setSni(_tmpSni);
        final boolean _tmpAllowInsecure;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfAllowInsecure);
        _tmpAllowInsecure = _tmp != 0;
        _result.setAllowInsecure(_tmpAllowInsecure);
        final String _tmpCountry;
        if (_cursor.isNull(_cursorIndexOfCountry)) {
          _tmpCountry = null;
        } else {
          _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
        }
        _result.setCountry(_tmpCountry);
        final int _tmpLatency;
        _tmpLatency = _cursor.getInt(_cursorIndexOfLatency);
        _result.setLatency(_tmpLatency);
        final long _tmpSubscriptionId;
        _tmpSubscriptionId = _cursor.getLong(_cursorIndexOfSubscriptionId);
        _result.setSubscriptionId(_tmpSubscriptionId);
        final long _tmpCreateTime;
        _tmpCreateTime = _cursor.getLong(_cursorIndexOfCreateTime);
        _result.setCreateTime(_tmpCreateTime);
        final boolean _tmpIsSelected;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsSelected);
        _tmpIsSelected = _tmp_1 != 0;
        _result.setSelected(_tmpIsSelected);
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
  public TrojanNode getNodeById(final long id) {
    final String _sql = "SELECT * FROM trojan_nodes WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfServer = CursorUtil.getColumnIndexOrThrow(_cursor, "server");
      final int _cursorIndexOfPort = CursorUtil.getColumnIndexOrThrow(_cursor, "port");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfSni = CursorUtil.getColumnIndexOrThrow(_cursor, "sni");
      final int _cursorIndexOfAllowInsecure = CursorUtil.getColumnIndexOrThrow(_cursor, "allowInsecure");
      final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
      final int _cursorIndexOfLatency = CursorUtil.getColumnIndexOrThrow(_cursor, "latency");
      final int _cursorIndexOfSubscriptionId = CursorUtil.getColumnIndexOrThrow(_cursor, "subscriptionId");
      final int _cursorIndexOfCreateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "createTime");
      final int _cursorIndexOfIsSelected = CursorUtil.getColumnIndexOrThrow(_cursor, "isSelected");
      final TrojanNode _result;
      if(_cursor.moveToFirst()) {
        _result = new TrojanNode();
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
        final String _tmpServer;
        if (_cursor.isNull(_cursorIndexOfServer)) {
          _tmpServer = null;
        } else {
          _tmpServer = _cursor.getString(_cursorIndexOfServer);
        }
        _result.setServer(_tmpServer);
        final int _tmpPort;
        _tmpPort = _cursor.getInt(_cursorIndexOfPort);
        _result.setPort(_tmpPort);
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _result.setPassword(_tmpPassword);
        final String _tmpSni;
        if (_cursor.isNull(_cursorIndexOfSni)) {
          _tmpSni = null;
        } else {
          _tmpSni = _cursor.getString(_cursorIndexOfSni);
        }
        _result.setSni(_tmpSni);
        final boolean _tmpAllowInsecure;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfAllowInsecure);
        _tmpAllowInsecure = _tmp != 0;
        _result.setAllowInsecure(_tmpAllowInsecure);
        final String _tmpCountry;
        if (_cursor.isNull(_cursorIndexOfCountry)) {
          _tmpCountry = null;
        } else {
          _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
        }
        _result.setCountry(_tmpCountry);
        final int _tmpLatency;
        _tmpLatency = _cursor.getInt(_cursorIndexOfLatency);
        _result.setLatency(_tmpLatency);
        final long _tmpSubscriptionId;
        _tmpSubscriptionId = _cursor.getLong(_cursorIndexOfSubscriptionId);
        _result.setSubscriptionId(_tmpSubscriptionId);
        final long _tmpCreateTime;
        _tmpCreateTime = _cursor.getLong(_cursorIndexOfCreateTime);
        _result.setCreateTime(_tmpCreateTime);
        final boolean _tmpIsSelected;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsSelected);
        _tmpIsSelected = _tmp_1 != 0;
        _result.setSelected(_tmpIsSelected);
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
  public int getNodeCount() {
    final String _sql = "SELECT COUNT(*) FROM trojan_nodes";
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

  @Override
  public List<TrojanNode> getNodesBySubscription(final long subscriptionId) {
    final String _sql = "SELECT * FROM trojan_nodes WHERE subscriptionId = ? ORDER BY createTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, subscriptionId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfServer = CursorUtil.getColumnIndexOrThrow(_cursor, "server");
      final int _cursorIndexOfPort = CursorUtil.getColumnIndexOrThrow(_cursor, "port");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfSni = CursorUtil.getColumnIndexOrThrow(_cursor, "sni");
      final int _cursorIndexOfAllowInsecure = CursorUtil.getColumnIndexOrThrow(_cursor, "allowInsecure");
      final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
      final int _cursorIndexOfLatency = CursorUtil.getColumnIndexOrThrow(_cursor, "latency");
      final int _cursorIndexOfSubscriptionId = CursorUtil.getColumnIndexOrThrow(_cursor, "subscriptionId");
      final int _cursorIndexOfCreateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "createTime");
      final int _cursorIndexOfIsSelected = CursorUtil.getColumnIndexOrThrow(_cursor, "isSelected");
      final List<TrojanNode> _result = new ArrayList<TrojanNode>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TrojanNode _item;
        _item = new TrojanNode();
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
        final String _tmpServer;
        if (_cursor.isNull(_cursorIndexOfServer)) {
          _tmpServer = null;
        } else {
          _tmpServer = _cursor.getString(_cursorIndexOfServer);
        }
        _item.setServer(_tmpServer);
        final int _tmpPort;
        _tmpPort = _cursor.getInt(_cursorIndexOfPort);
        _item.setPort(_tmpPort);
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _item.setPassword(_tmpPassword);
        final String _tmpSni;
        if (_cursor.isNull(_cursorIndexOfSni)) {
          _tmpSni = null;
        } else {
          _tmpSni = _cursor.getString(_cursorIndexOfSni);
        }
        _item.setSni(_tmpSni);
        final boolean _tmpAllowInsecure;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfAllowInsecure);
        _tmpAllowInsecure = _tmp != 0;
        _item.setAllowInsecure(_tmpAllowInsecure);
        final String _tmpCountry;
        if (_cursor.isNull(_cursorIndexOfCountry)) {
          _tmpCountry = null;
        } else {
          _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
        }
        _item.setCountry(_tmpCountry);
        final int _tmpLatency;
        _tmpLatency = _cursor.getInt(_cursorIndexOfLatency);
        _item.setLatency(_tmpLatency);
        final long _tmpSubscriptionId;
        _tmpSubscriptionId = _cursor.getLong(_cursorIndexOfSubscriptionId);
        _item.setSubscriptionId(_tmpSubscriptionId);
        final long _tmpCreateTime;
        _tmpCreateTime = _cursor.getLong(_cursorIndexOfCreateTime);
        _item.setCreateTime(_tmpCreateTime);
        final boolean _tmpIsSelected;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsSelected);
        _tmpIsSelected = _tmp_1 != 0;
        _item.setSelected(_tmpIsSelected);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getNodeCountBySubscription(final long subscriptionId) {
    final String _sql = "SELECT COUNT(*) FROM trojan_nodes WHERE subscriptionId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, subscriptionId);
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
