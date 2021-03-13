package com.yiyi.crm;

import com.yiyi.crm.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {
  /**
   * Index page.
   * @return
   */
  @RequestMapping("index")
  public String index(){
    return "index";
  }

  @RequestMapping("welcome")
  public String welcome(){
    return "welcome";
  }

  /**
   * Management page.
   * @return
   */
  @RequestMapping("main")
  public String main(){
    return "main";
  }
}
