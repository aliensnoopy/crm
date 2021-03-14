package com.yiyi.crm.exceptions;

import com.alibaba.fastjson.JSON;
import com.yiyi.crm.base.ResultInfo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {

  @Override
  public ModelAndView resolveException(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      Object handler,
      Exception e) {
    if (e instanceof NoLoginException) {
      ModelAndView modelAndView = new ModelAndView("redirect:/index");
      return modelAndView;
    }

    ModelAndView modelAndView = new ModelAndView("error");
    modelAndView.addObject("code", 500);
    modelAndView.addObject("msg", "Exception occurred, please retry...");

    if (handler instanceof HandlerMethod) {
      HandlerMethod handlerMethod = (HandlerMethod) handler;
      ResponseBody responseBody =
          handlerMethod.getMethod().getDeclaredAnnotation(ResponseBody.class);
      if (responseBody == null) {
        if (e instanceof ParamsException) {
          ParamsException ex = (ParamsException) e;
          modelAndView.addObject("code", ex.getCode());
          modelAndView.addObject("msg", ex.getMsg());
        }
        return modelAndView;
      } else {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(500);
        resultInfo.setMsg("Exception occurred, please retry...");

        if (e instanceof ParamsException) {
          ParamsException ex = (ParamsException) e;
          resultInfo.setCode(ex.getCode());
          resultInfo.setMsg(ex.getMsg());
        }
        httpServletResponse.setContentType("application/json:charset=UTF-8");
        try (PrintWriter printWriter = httpServletResponse.getWriter()) {
          printWriter.write(JSON.toJSONString(resultInfo));
        } catch (IOException ex) {
          ex.printStackTrace();
        }
        return null;
      }
    }

    return modelAndView;
  }
}
