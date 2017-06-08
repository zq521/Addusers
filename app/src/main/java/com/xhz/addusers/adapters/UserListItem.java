package com.xhz.addusers.adapters;

import android.view.View;
import android.widget.TextView;

import com.xhz.addusers.R;

/**
 * mail：727319870@qq.com
 * Created by ${轩韩子} on 2017/6/6.
 * 16:03
 * UserListItem实体类
 */

public  class UserListItem  {
    private TextView tvmame, tvage;

    public UserListItem(View itemView) {
        tvmame = (TextView) itemView.findViewById(R.id.tvName);
        tvage = (TextView) itemView.findViewById(R.id.tvAge);
    }

    public TextView getTvmame() {
        return tvmame;
    }

    public TextView getTvage() {
        return tvage;
    }

}


