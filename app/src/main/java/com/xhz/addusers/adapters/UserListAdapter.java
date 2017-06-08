package com.xhz.addusers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xhz.addusers.R;
import com.xhz.addusers.db.DbCursor;

/**
 * mail：727319870@qq.com
 * Created by ${轩韩子} on 2017/6/6.
 * 15:59
 * listView适配器
 */
public class UserListAdapter extends BaseAdapter {

    private DbCursor cursor;
    private final Context context;

    public UserListAdapter(DbCursor cursor, Context context) {
        setCursor(cursor);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }


    public void setCursor(DbCursor cursor) {
        if (this.cursor != null) {
            this.cursor.close();
        }
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cursor != null ? cursor.getCount() : 0;
    }

    @Override
    public DbCursor getItem(int position) {
        this.cursor.moveToPosition(position);
        return this.cursor;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //获取item布局
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_view, null);
            //设置标签
            convertView.setTag(new UserListItem(convertView));

        }
        //获取项目
        DbCursor cursor=getItem(position);
        //实例化UserListItem
        UserListItem itemView = (UserListItem) convertView.getTag();
        //设置数据
        itemView.getTvmame().setText(cursor.getName());
        itemView.getTvage().setText(String.valueOf(cursor.getAge()));
        return convertView;
    }
}
