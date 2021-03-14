package com.yiyi.crm.interceptor;

import com.yiyi.crm.dao.UserMapper;
import com.yiyi.crm.exceptions.NoLoginException;
import com.yiyi.crm.utils.LoginUserUtil;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class NoLoginInterceptor extends HandlerInterceptorAdapter {
  @Resource
  private UserMapper userMapper;

  @Override
  public boolean preHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler) throws Exception {
    Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
    if (userId == null || userMapper.selectByPrimaryKey(userId) == null) {
      throw new NoLoginException();
    }
    return true;
  }
}
