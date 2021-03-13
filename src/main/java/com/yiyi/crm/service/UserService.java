package com.yiyi.crm.service;

import com.yiyi.crm.base.BaseService;
import com.yiyi.crm.dao.UserMapper;
import com.yiyi.crm.model.UserModel;
import com.yiyi.crm.utils.AssertUtil;
import com.yiyi.crm.utils.Md5Util;
import com.yiyi.crm.utils.UserIDBase64;
import com.yiyi.crm.vo.User;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Integer> {
  @Resource
  private UserMapper userMapper;

  public UserModel userLogin(String userName, String userPwd) {
    checkLoginParams(userName, userPwd);
    User user = userMapper.queryUserByName(userName);
    AssertUtil.isTrue(user == null, "User account does not exist.");
    checkPassword(user.getUserPwd(), userPwd);
    return buildUserModel(user);
  }

  private UserModel buildUserModel(User user) {
    return new UserModel(
        UserIDBase64.encoderUserID(user.getId()),
        user.getUserName(),
        user.getTrueName());
  }

  /**
   * We need to encode the password first and then compare the password.
   * @param expectedPwd
   * @param actualPwd
   */
  private void checkPassword(String expectedPwd, String actualPwd) {
    String encryptedPwd = Md5Util.encode(actualPwd);
    AssertUtil.isTrue(!expectedPwd.equals(encryptedPwd), "Password is not correct.");
  }

  private void checkLoginParams(String userName, String userPwd) {
    AssertUtil.isTrue(StringUtils.isBlank(userName), "User name cannot be empty.");
    AssertUtil.isTrue(StringUtils.isBlank(userPwd), "User password cannot be empty.");
  }
}
