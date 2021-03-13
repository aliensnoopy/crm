package com.yiyi.crm.model;

public class UserModel {
  private final Integer userId;
  private final String userName;
  private final String trueName;

  public UserModel(Integer userId, String userName, String trueName) {
    this.userId = userId;
    this.userName = userName;
    this.trueName = trueName;
  }

  public Integer getUserId() {
    return userId;
  }

  public String getUserName() {
    return userName;
  }

  public String getTrueName() {
    return trueName;
  }
}
