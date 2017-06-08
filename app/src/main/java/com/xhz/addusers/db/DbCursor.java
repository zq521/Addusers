package com.xhz.addusers.db;

import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteQuery;

/**
 * mail：727319870@qq.com
 * Created by ${轩韩子} on 2017/6/7.
 * 10:20
 * 自定义查询数据UserCursor
 */

public class DbCursor extends SQLiteCursor {

    public DbCursor(SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
        super(driver, editTable, query);
    }

    //获得数据
    public String getName() {
        return getString(getColumnIndex(DbConnertor.NAME_COLUMN_NAME));
    }

    //获得Id
    public int getId() {
        return getInt(getColumnIndex(DbConnertor.ID_COLUMN_NAME));
    }

    public int getAge() {
        return getInt(getColumnIndex(DbConnertor.AGE_COLUMN_NAME));
    }



}
