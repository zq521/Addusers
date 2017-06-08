package com.xhz.addusers.models;

/**
 * mail：727319870@qq.com
 * Created by ${轩韩子} on 2017/6/8.
 * 11:00
 * User的实体类
 */

public class UserGRoup {
    private int id;
    private String name;

    public UserGRoup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
