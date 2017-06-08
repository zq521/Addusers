package com.xhz.addusers.controlles;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.xhz.addusers.R;
import com.xhz.addusers.databinding.FragmentUserGroupListBinding;
import com.xhz.addusers.db.DbConnertor;
import com.xhz.addusers.db.DbCursor;
import com.xhz.addusers.fragment.EditGroupFragment;
import com.xhz.addusers.fragment.UserGroupListFragment;
import com.xhz.addusers.models.GroupListOperationsMenuItem;
import com.xhz.addusers.models.UserGRoup;

import java.util.ArrayList;
import java.util.List;

/**
 * mail：727319870@qq.com
 * Created by ${轩韩子} on 2017/6/8.
 * 13:43
 * <p>
 * 用户组控制
 */

public class UserGroupListFragmentController {

    private FragmentUserGroupListBinding binding;
    private UserGroupListFragment userGroupFragment;
    private DbConnertor dbConnertor;

    public UserGroupListFragmentController(FragmentUserGroupListBinding binding, UserGroupListFragment userGroupFragment) {
        this.binding = binding;
        this.userGroupFragment = userGroupFragment;
        dbConnertor = new DbConnertor(userGroupFragment.getContext());
        addLiseteners();
        readGroupFromDB();
    }

    /**
     * @param v
     */
    public void btnAddGroupClicked(View v) {
        binding.getRoot().setVisibility(View.GONE);
        //添加用户组界面
        userGroupFragment.getFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, EditGroupFragment.newInstance())
                .addToBackStack(EditGroupFragment.NAME)
                .commit();


    }

    /**
     * 跟数据库读取数据
     */
    private void readGroupFromDB() {
        DbCursor cursor = dbConnertor.quesryGroups();
        List<UserGRoup> items = new ArrayList<>();
        while (cursor.moveToNext()) {
            //得到用户名与id
            items.add(new UserGRoup(cursor.getId(), cursor.getName()));
        }
        cursor.close();
        //为用户组设置适配器，items为数据
        binding.groupListView.setAdapter(new ArrayAdapter<UserGRoup>(userGroupFragment.getContext(), android.R.layout.simple_list_item_1, items));
    }

    public void onDestoty() {
        dbConnertor.close();
    }

    //重新读取数据库
    public void onBackToFragment() {
        readGroupFromDB();
        addLiseteners();
    }

    //监听事件
    private void addLiseteners() {
        addListItemLongListeners();
    }

    //长按点击事件
    private void addListItemLongListeners() {
        binding.groupListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //获取适配器内容
                final UserGRoup group = (UserGRoup) binding.groupListView.getAdapter().getItem(position);
                final int groupId = group.getId();
                //为组类修改表操作菜单项添加布局的适配器
                final ArrayAdapter<GroupListOperationsMenuItem> adapter = new ArrayAdapter<GroupListOperationsMenuItem>(userGroupFragment.getContext()
                        , android.R.layout.simple_list_item_1
                        , new GroupListOperationsMenuItem[]{
                        new GroupListOperationsMenuItem(GroupListOperationsMenuItem.OPERATION_EDIT, "编辑")
                        , new GroupListOperationsMenuItem(GroupListOperationsMenuItem.OPERATION_DELETE, "删除")

                });
                //提示框
                new AlertDialog.Builder(userGroupFragment.getContext())
                        .setTitle("组操作选项")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (adapter.getItem(which).getOperation()) {
                                    //点击编辑
                                    case GroupListOperationsMenuItem.OPERATION_EDIT:
                                        //加载编辑布局
                                        userGroupFragment.getFragmentManager()
                                                .beginTransaction()
                                                .add(R.id.fragmentContainer, EditGroupFragment.newInstance(group.getId(), group.getName()))
                                                .addToBackStack(EditGroupFragment.NAME)
                                                .commit();
                                        break;
                                    //点击删除
                                    case GroupListOperationsMenuItem.OPERATION_DELETE:
                                        //根据用户组ID删除此项
                                        dbConnertor.deleteGroup(groupId);
                                        //重新加载数据
                                        readGroupFromDB();
                                        break;
                                }
                            }
                        })
                        .setNegativeButton("关闭", null)
                        .show();
                return true;
            }
        });
    }

}
