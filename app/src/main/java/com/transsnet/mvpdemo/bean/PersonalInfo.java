package com.transsnet.mvpdemo.bean;

import java.io.Serializable;

/**
 * Author:  zengfeng
 * Time  :  2020/1/10 17:58
 * Des   :
 */
public class PersonalInfo implements Serializable {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
