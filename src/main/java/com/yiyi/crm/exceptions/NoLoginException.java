package com.yiyi.crm.exceptions;

public class NoLoginException extends RuntimeException {
  private Integer code = 300;
  private String msg = "User not logged in!";

  public NoLoginException() {
    super("User not logged in!");
  }

  public NoLoginException(String msg) {
    super(msg);
    this.msg = msg;
  }

  public NoLoginException(Integer code) {
    super("User not logged in!");
    this.code = code;
  }

  public NoLoginException(Integer code, String msg) {
    super(msg);
    this.code = code;
    this.msg = msg;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
