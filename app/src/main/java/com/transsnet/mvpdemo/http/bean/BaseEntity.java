package com.transsnet.mvpdemo.http.bean;

public class BaseEntity<T> {

  public static final String SUCCESS = "success";
  public static final String ERROR = "INTERFACE_ERROR";
  public static final String NOT_LOGIN = "not_login";
  public static final String FAIL = "fail";
  public static final String FAIL_HASAUTH = "100000";
  public static final String FALI_HASBINDBANKACCOUNT = "4255";
  public static final String APP_UPDATE = "not_serve_app_ver";//{"status":"not_serve_app_ver","meg":"请升级到最新版本，体验更快捷的服务!","data":{},"requestId":"ca81fa61-314a-4076-8ea9-374c52a777c1"}

  public String status;
  public String meg;
  public T data;
  private String requestId;

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMsg() {
    return meg;
  }

  public void setMsg(String msg) {
    this.meg = msg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
