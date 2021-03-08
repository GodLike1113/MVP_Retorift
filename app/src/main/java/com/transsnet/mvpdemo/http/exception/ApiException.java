package com.transsnet.mvpdemo.http.exception;

public class ApiException extends IllegalArgumentException {

  private int code;

  public ApiException(int code, String msg) {
    super(msg);
    this.code = code;
  }

  public int getStatus() {
    return code;
  }
}