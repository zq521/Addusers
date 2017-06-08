package com.xhz.addusers.controlles;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.xhz.addusers.databinding.FragmentEditGroupBinding;
import com.xhz.addusers.db.DbConnertor;
import com.xhz.addusers.fragment.EditGroupFragment;

/**
 * mail：727319870@qq.com
 * Created by ${轩韩子} on 2017/6/8.
 * 15:05
 * 添加组界面类
 */

public class EditGroupFragmentContontroller {
    private FragmentEditGroupBinding binding;
    private EditGroupFragment editGroupFragment;
    private DbConnertor dbConnertor;

    public EditGroupFragmentContontroller(FragmentEditGroupBinding binding, EditGroupFragment editGroupFragment) {
        this.binding = binding;
        this.editGroupFragment = editGroupFragment;
        initUI();
        dbConnertor = new DbConnertor(editGroupFragment.getContext());

    }

    private void initUI() {
        //得到输入框内容
        binding.groupNameINput.setText(editGroupFragment.getGroupName());

    }

    public void btnCanceClicked(View v) {
        //返回到栈顶部
        editGroupFragment.getFragmentManager().popBackStack();

    }


    public void btnSaveClicked(View v) {

        if (!TextUtils.isEmpty(binding.groupNameINput.getText())) {

            String groupName = binding.groupNameINput.getText().toString();
            int groupId = editGroupFragment.getGroupId();

            // 因为ID默认值是0。所有如果groupid为0时将应该添加新组，否则是修改该组;
            if (groupId > 0) {
                //修改数据，把数据显示在输入框内
                dbConnertor.editGroup(groupId, groupName);
            } else {
                //添加数据
                dbConnertor.addGroup(groupName);
            }
        } else {
            Toast.makeText(editGroupFragment.getContext(), "请输入内容", Toast.LENGTH_SHORT).show();
        }
        editGroupFragment.getFragmentManager().popBackStack();
    }

    public void onDestroy() {
        dbConnertor.close();
    }


}
