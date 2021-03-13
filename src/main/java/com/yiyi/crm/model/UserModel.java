package com.yiyi.crm.model;

public class UserModel {
  private final String encryptedUserId;
  private final String userName;
  private final String trueName;

  public UserModel(String encryptedUserId, String userName, String trueName) {
    this.encryptedUserId = encryptedUserId;
    this.userName = userName;
    this.trueName = trueName;
  }

  public String getEncryptedUserId() {
    return encryptedUserId;
  }

  public String getUserName() {
    return userName;
  }

  public String getTrueName() {
    return trueName;
  }
}
