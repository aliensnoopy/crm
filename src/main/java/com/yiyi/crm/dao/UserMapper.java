package com.yiyi.crm.dao;

import com.yiyi.crm.base.BaseMapper;
import com.yiyi.crm.vo.User;

public interface UserMapper extends BaseMapper<User, Integer> {
  public User queryUserByName(String userName);
}
