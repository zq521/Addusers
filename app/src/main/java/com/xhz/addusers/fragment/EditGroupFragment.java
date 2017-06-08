package com.xhz.addusers.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xhz.addusers.controlles.EditGroupFragmentContontroller;
import com.xhz.addusers.databinding.FragmentEditGroupBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditGroupFragment extends AbstracFragment {

    private FragmentEditGroupBinding binding;
    public static final String NAME = "EditGroupFragment";
    private EditGroupFragmentContontroller contontroller;
    private static final String KEY_GROUP_ID = "groupId";
    private static final String KEY_GROUP_NAME = "groupNAme";

    public EditGroupFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentEditGroupBinding.inflate(inflater);
        contontroller = new EditGroupFragmentContontroller(binding, this);
        binding.setContorller(contontroller);
        setBinding(binding);
        return binding.getRoot();
    }



    /**
     * 根据组ID和组名创建Framgment组实例
     *
     * @param groupId
     * @param groupName
     * @return
     */
    public static EditGroupFragment newInstance(int groupId, String groupName) {
        EditGroupFragment fragment = new EditGroupFragment();
        Bundle data = new Bundle();
        data.putString(KEY_GROUP_NAME, groupName);
        data.putInt(KEY_GROUP_ID, groupId);
        fragment.setArguments(data);
        return fragment;
    }

    public static EditGroupFragment newInstance() {
        return new EditGroupFragment();
    }

    /**
     * 获取组名
     *
     * @return
     */
    public String getGroupName() {
        Bundle args = getArguments();
        return args != null ? args.getString(KEY_GROUP_NAME, "") : "";
    }

    /**
     * 获取用户组Id 如果得到的值是0 ，则认为没有用户组ID，则保存操作添加新组,而不是修改
     *
     * @return
     */
    public int getGroupId() {
        Bundle args = getArguments();
        return args != null ? args.getInt(KEY_GROUP_ID, 0) : 0;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        contontroller.onDestroy();
    }

}
