package com.yiyi.crm.controller;

import com.yiyi.crm.base.BaseController;
import com.yiyi.crm.base.ResultInfo;
import com.yiyi.crm.exceptions.ParamsException;
import com.yiyi.crm.model.UserModel;
import com.yiyi.crm.service.UserService;
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
}
