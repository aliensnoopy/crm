package com.yiyi.crm.controller;

import com.yiyi.crm.base.BaseController;
import com.yiyi.crm.base.ResultInfo;
import com.yiyi.crm.model.UserModel;
import com.yiyi.crm.service.UserService;
import com.yiyi.crm.utils.LoginUserUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("login")
  @ResponseBody
  public ResultInfo userLogin(@RequestParam String userName, @RequestParam String userPwd) {
    return success("Login successfully.", userService.userLogin(userName, userPwd));
  }

  @PostMapping("updatepwd")
  @ResponseBody
  public ResultInfo updateUserPwd(
      HttpServletRequest request,
      @RequestParam String oldPwd,
      @RequestParam String newPwd,
      @RequestParam String repeatPwd) {
    Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
    userService.updatePwd(userId, oldPwd, newPwd, repeatPwd);
    return success("Update password successfully.");
  }

  @RequestMapping("toPasswordPage")
  public String toPasswordPage() {
    return "user/password";
  }
}
