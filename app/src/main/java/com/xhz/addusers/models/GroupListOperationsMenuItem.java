package com.xhz.addusers.models;

/**
 * mail：727319870@qq.com
 * Created by ${轩韩子} on 2017/6/8.
 * 15:51
 * 提示框内的item类
 */

public class GroupListOperationsMenuItem {

    public static final int OPERATION_DELETE = 1;
    public static final int OPERATION_EDIT = 2;
    private String name;
    private int operation;

    public GroupListOperationsMenuItem(int operation,String name) {
        this.operation = operation;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getOperation() {
        return operation;
    }


    @Override
    public String toString() {
        return getName();
    }
}
