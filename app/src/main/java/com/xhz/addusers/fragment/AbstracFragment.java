package com.xhz.addusers.fragment;

import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * mail：727319870@qq.com
 * Created by ${轩韩子} on 2017/6/8.
 * 11:38
 * 该类为所有自定义fragment 的基类，用于封装一些公用函数
 */

public abstract class AbstracFragment extends Fragment {

    //视图的数据绑定
    private ViewDataBinding binding;

    public ViewDataBinding getBinding() {
        return binding;
    }

    public void setBinding(ViewDataBinding binding) {
        this.binding = binding;
    }

    /**
     *  当fragmentManger后退导航到fragment时触发该事件
     */
    public void onBackToFragment(){
        //判断是否有绑定数据
        if (getBinding() != null) {
            //设置可见
            getBinding().getRoot().setVisibility(View.VISIBLE);

        }
    }


}
