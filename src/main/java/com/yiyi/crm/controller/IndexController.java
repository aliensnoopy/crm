package com.yiyi.crm.controller;

import com.yiyi.crm.base.BaseController;
import com.yiyi.crm.service.UserService;
import com.yiyi.crm.utils.LoginUserUtil;
import com.yiyi.crm.vo.User;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {
  private UserService userService;

  @Autowired
  public IndexController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Index page.
   *
   * @return
   */
  @RequestMapping("index")
  public String index() {
    return "index";
  }

  @RequestMapping("welcome")
  public String welcome() {
    return "welcome";
  }

  /**
   * Management page.
   *
   * @return
   */
  @RequestMapping("main")
  public String main(HttpServletRequest request) {
    Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
    User user = userService.selectByPrimaryKey(userId);
    request.getSession().setAttribute("user", user);
    return "main";
  }
}
