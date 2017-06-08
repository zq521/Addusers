package com.xhz.addusers.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xhz.addusers.controlles.UserGroupListFragmentController;
import com.xhz.addusers.databinding.FragmentUserGroupListBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserGroupListFragment extends AbstracFragment {

    private FragmentUserGroupListBinding binding;
    private UserGroupListFragmentController controller;

    public UserGroupListFragment() {
        // Required empty public constructor

    }

    @Override
    public void onDestroy() {
        controller.onDestoty();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //为此布局使用数据绑定
        binding = FragmentUserGroupListBinding.inflate(inflater);
        controller = new UserGroupListFragmentController(binding, this);
        binding.setContorller(controller);

        setBinding(binding);

        return binding.getRoot();

    }


    @Override
    public void onBackToFragment() {
        super.onBackToFragment();
        controller.onBackToFragment();
    }
}
