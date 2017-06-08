package com.xhz.addusers.controlles;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.xhz.addusers.act.EditUserActivity;
import com.xhz.addusers.act.MainActivity;
import com.xhz.addusers.adapters.UserListAdapter;
import com.xhz.addusers.databinding.ActivityMainBinding;
import com.xhz.addusers.db.DbConnertor;

/**
 * mail：727319870@qq.com
 * Created by ${轩韩子} on 2017/6/7.
 * 15:29
 */

public class UserLIstContorLLer {

    private ActivityMainBinding binding;
    private MainActivity mainActivity;
    private static final int REQUEST_CODE_ADD_USER = 2;
    private static final int REQUEST_CODE_EDIT_USER = 3;
    private DbConnertor dbConnertor;
    private UserListAdapter adapter;

    public UserLIstContorLLer(ActivityMainBinding binding, MainActivity mainActivity) {
        this.binding = binding;
        this.mainActivity = mainActivity;
        dbConnertor = new DbConnertor(mainActivity);
        //config
//        adapter = new UserListAdapter(null, mainActivity);
//        binding.userlist.setAdapter(adapter);
//        readFromDb();
//        addListeners();


    }

    private void addListeners() {
        addUserItemLongClickListener();
        addUserItemClickListener();
    }

   private void addUserItemClickListener() {
//        binding.userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                DbCursor cursor = adapter.getItem(position);
//                //修改过后的数据
//                Intent intent = new Intent(mainActivity, EditUserActivity.class);
//                intent.putExtra(EditUserActivity.KEY_USER_NAME, cursor.getName());
//                intent.putExtra(EditUserActivity.KEY_USER_AGE, cursor.getAge());
//                intent.putExtra(EditUserActivity.KEY_USER_ID, cursor.getId());
//                mainActivity.startActivityForResult(intent, REQUEST_CODE_EDIT_USER);
//            }
//        });
    }

    private void addUserItemLongClickListener() {
//        binding.userlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                DbCursor cursor = adapter.getItem(position);
//                final int currentItemIdInDb = cursor.getId();
//
//                new AlertDialog.Builder(mainActivity)
//                        .setTitle("t提示")
//                        .setMessage("你确定要删除吗？")
//                        .setPositiveButton("是的", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dbConnertor.deleteUser(currentItemIdInDb);
//                                readFromDb();
//                            }
//                        }).setNegativeButton("关闭", null).show();
//
//                return true;
//            }
//        });
    }

    public void btnAddUserClicked(View v) {
        mainActivity.startActivityForResult(new Intent(mainActivity, EditUserActivity.class), REQUEST_CODE_ADD_USER);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_ADD_USER:
                switch (resultCode) {
                    case EditUserActivity.RESULT_SAVE:
                        //添加数据
                        insertUserToDb(data.getStringExtra(EditUserActivity.KEY_USER_NAME),
                                data.getIntExtra(EditUserActivity.KEY_USER_AGE, 0)
                        );
                        break;
                    case EditUserActivity.RESULT_CLOSE:
                        Toast.makeText(mainActivity, "没有可保存的数据", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            case REQUEST_CODE_EDIT_USER:
                switch (resultCode) {
                    //保存更新过的数据
                    case EditUserActivity.RESULT_SAVE:
                        int id = data.getIntExtra(EditUserActivity.KEY_USER_ID, 0);
                        if (id > 0) {
                            dbConnertor.updateUser(id, data.getStringExtra(EditUserActivity.KEY_USER_NAME), data.getIntExtra(EditUserActivity.KEY_USER_AGE, 1));
                            readFromDb();
                        }
                        break;
                }
                break;
        }
    }


    private void readFromDb() {
        adapter.setCursor(dbConnertor.queryUsers());
    }


    private void insertUserToDb(String name, int age) {
        dbConnertor.insertUser(name, age);
        readFromDb();
    }

    public void onDestoty() {
        dbConnertor.close();
    }
}
