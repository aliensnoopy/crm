package com.yiyi.crm.controller;

import com.yiyi.crm.base.BaseController;
import com.yiyi.crm.base.ResultInfo;
import com.yiyi.crm.exceptions.ParamsException;
import com.yiyi.crm.model.UserModel;
import com.yiyi.crm.service.UserService;
import com.yiyi.crm.utils.LoginUserUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    ResultInfo resultInfo = new ResultInfo();
    try {
      UserModel userModel = userService.userLogin(userName, userPwd);
      // Returns the user information back to frontend by storing info in ResultInfo.
      resultInfo.setResult(userModel);
    } catch (ParamsException e) {
      resultInfo.setCode(e.getCode());
      resultInfo.setMsg(e.getMsg());
      e.printStackTrace();
    } catch (Exception e) {
      resultInfo.setCode(500);
      resultInfo.setMsg("Login failed.");
      e.printStackTrace();
    }
    return resultInfo;
  }

  @PostMapping("updatepwd")
  @ResponseBody
  public ResultInfo updateUserPwd(
      HttpServletRequest request,
      @RequestParam String oldPwd,
      @RequestParam String newPwd,
      @RequestParam String repeatPwd) {
    ResultInfo resultInfo = new ResultInfo();
    try {
      Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
      userService.updatePwd(userId, oldPwd, newPwd, repeatPwd);
      resultInfo.setMsg("Update password successfully.");
    } catch (ParamsException e) {
      resultInfo.setCode(e.getCode());
      resultInfo.setMsg(e.getMsg());
      e.printStackTrace();
    } catch (Exception e) {
      resultInfo.setCode(500);
      resultInfo.setMsg("Failed to update password.");
      e.printStackTrace();
    }
    return resultInfo;
  }

  @RequestMapping("toPasswordPage")
  public String toPasswordPage() {
    return "user/password";
  }
}
