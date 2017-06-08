package com.xhz.addusers.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;

/**
 * mail：727319870@qq.com
 * Created by ${轩韩子} on 2017/6/7.
 * 11:30
 * 创建数据库DbConnertor
 */
public class DbConnertor extends SQLiteOpenHelper {

    public static final String DB_NAME = "users.db";//数据库文件名
    public static final String TABLE_NAME = "user";//表名
    public static final String ID_COLUMN_NAME = "_id";//自增id
    public static final String NAME_COLUMN_NAME = "name";
    public static final String AGE_COLUMN_NAME = "age";
    public static final int DB_VERSION = 2;//版本号
    public static final String TABLE_NAME_GROUP = "user_group";
    public static final String COLUMN_NAME_GROUP_ID = "group_id";
    private SQLiteDatabase writableDatabase;
    private SQLiteDatabase readableDatabase;
    private ContentValues cvs;


    public DbConnertor(Context context) {
        super(context, DB_NAME, new SQLiteDatabase.CursorFactory() {
            @Override
            public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {
                return new DbCursor(masterQuery, editTable, query);
            }
        }, DB_VERSION);

        writableDatabase = getWritableDatabase();
        readableDatabase = getReadableDatabase();

    }

    //创建数据表格
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME_GROUP + "(" +
                ID_COLUMN_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAME_COLUMN_NAME + " TEXT NOT NULL DEFAULT \"default group\")");
        initGroupData(db);

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                ID_COLUMN_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAME_COLUMN_NAME + " TEXT NOT NULL DEFAULT NAME," +
                AGE_COLUMN_NAME + " INTEGER DEFAULT 1," +
                COLUMN_NAME_GROUP_ID + " INTEGER DEFAULT 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //upgrade from 1 to 2
        if (oldVersion == 1 && newVersion == 2) {
            db.execSQL("CREATE TABLE " + TABLE_NAME_GROUP + "(" +
                    ID_COLUMN_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NAME_COLUMN_NAME + " TEXT NOT NULL DEFAULT \"default group\")");
            initGroupData(db);

            //upgrade user table
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_NAME_GROUP_ID + " INTEGER DEFAULT 1");
        }
    }

    //添加数据
    public void insertUser(String name, int age) {
        //数据编辑器
        cvs = new ContentValues();
        cvs.put(NAME_COLUMN_NAME, name);
        cvs.put(AGE_COLUMN_NAME, age);
        writableDatabase.insert(TABLE_NAME, "", cvs);
    }

    public void close() {
        writableDatabase.close();
    }

    //查询数据
    public DbCursor queryUsers() {
        return (DbCursor) readableDatabase.query("user", null, null, null, null, null, null);
    }

    /**
     * 根据id删除该条数据
     * 【？】代表指定后面条件
     *
     * @param id
     */
    public void deleteUser(int id) {
        writableDatabase.delete(TABLE_NAME, "_id=?", new String[]{String.valueOf(id)});

    }

    //添加一个组
    public void addGroup(String name) {
        ContentValues cvs = new ContentValues();

        cvs.put(NAME_COLUMN_NAME, name);
        getWritableDatabase().insert(TABLE_NAME_GROUP, null, cvs);

    }

    public DbCursor quesryGroups() {
        return (DbCursor) readableDatabase.query(TABLE_NAME_GROUP, null, null, null, null, null, null);
    }

    //修改数据
    public void updateUser(int id, String name, int age) {

        cvs = new ContentValues();
        cvs.put(NAME_COLUMN_NAME, name);
        cvs.put(AGE_COLUMN_NAME, age);
        writableDatabase.update(TABLE_NAME, cvs, ID_COLUMN_NAME + "=?", new String[]{String.valueOf(id)});

    }

    /**
     * 初始化用户组数据
     */
    private void initGroupData(SQLiteDatabase db) {
        cvs = new ContentValues();
        cvs.put(ID_COLUMN_NAME, 1);
        cvs.put(NAME_COLUMN_NAME, "default group");
        db.insert(TABLE_NAME_GROUP, null, cvs);
    }

    //根据ID删除用户组
    public void deleteGroup(int id) {
        readableDatabase.delete(TABLE_NAME_GROUP, ID_COLUMN_NAME + "=?", new String[]{String.valueOf(id)});

    }

    /**
     * 更新数据到数据库
     * @param id
     * @param name
     */
    public void editGroup(int id, String name) {
        ContentValues values = new ContentValues();
        values.put(NAME_COLUMN_NAME, name);

        getWritableDatabase().update(TABLE_NAME_GROUP, values,ID_COLUMN_NAME+"=?",new String[]{String.valueOf(id)});



    }
}



