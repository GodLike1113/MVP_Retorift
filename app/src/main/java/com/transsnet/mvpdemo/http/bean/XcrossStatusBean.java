package com.transsnet.mvpdemo.http.bean;

import java.io.Serializable;

/**
 * Author:  zengfeng
 * Time  :  2019/10/15 11:23
 * Des   :
 */
public class XcrossStatusBean implements Serializable {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
