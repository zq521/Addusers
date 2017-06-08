package com.xhz.addusers.controlles;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.xhz.addusers.R;
import com.xhz.addusers.act.MainActivity;
import com.xhz.addusers.databinding.ActivityMainBinding;
import com.xhz.addusers.fragment.AbstracFragment;
import com.xhz.addusers.fragment.UserGroupListFragment;

/**
 * mail：727319870@qq.com
 * Created by ${轩韩子} on 2017/6/8.
 * 10:38
 */

public class MainControlles {
    private ActivityMainBinding binding;
    private MainActivity mainActivity;
    private AbstracFragment currentActiviteFragmnet;
    private FragmentManager supportFragmentManager;
    private UserGroupListFragment userGroupListFragment;

    public MainControlles(ActivityMainBinding binding, MainActivity mainActivity) {
        this.binding = binding;
        this.mainActivity = mainActivity;

        supportFragmentManager = mainActivity.getSupportFragmentManager();
        userGroupListFragment = new UserGroupListFragment();
        addListeners();
         //添加用户组
        mainActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, userGroupListFragment)
                .commit();


    }
   //添加监听
    private void addListeners() {
        //回调函数，自动注册，为注册时则销毁
        mainActivity.getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentResumed(FragmentManager fm, Fragment f) {
                currentActiviteFragmnet = (AbstracFragment) f;

                super.onFragmentResumed(fm, f);
            }

        }, false);
        //添加栈改变监听器
        mainActivity.getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                //判断返回栈数目
                if (supportFragmentManager.getBackStackEntryCount() > 0) {
                    //System.out.println(supportFragmentManager.getBackStackEntryAt(0).getClass());

                    FragmentManager.BackStackEntry topSE = supportFragmentManager.getBackStackEntryAt(supportFragmentManager.getBackStackEntryCount() - 1);
                     //如果属于，则显示
                    if (topSE instanceof AbstracFragment) {

                        ((AbstracFragment) topSE).onBackToFragment();

                    }
                } else {
                    //否则返回
                    userGroupListFragment.onBackToFragment();

                }
            }
        });
    }

}
