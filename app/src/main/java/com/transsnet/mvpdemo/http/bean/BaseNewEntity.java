package com.transsnet.mvpdemo.http.bean;

public class BaseNewEntity<T> {
    /**
     * data : Y
     * status : {"code":0,"msg":"OK"}
     */
    public static final int SUCCESS = 0;
    public static final int API_EXCEPTION = 1001;
    public static final int CONNECTION_EXCEPTION = 1002;
    public static final int TIMEOUT_EXCEPTION = 1003;
    public static final int WEAKNETWORK_EXCEPTION = 1004;
    public static final int JSONSYNTAXEXCEPTION = 1005;
    public static final int HTTP_EXCEPTION = 1006;
    public static final int CONCURRENTMODIFICATION_EXCEPTION = 1007;

    private T data;
    private XcrossStatusBean status;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public XcrossStatusBean getStatus() {
        return status;
    }

    public void setStatus(XcrossStatusBean status) {
        this.status = status;
    }
}
