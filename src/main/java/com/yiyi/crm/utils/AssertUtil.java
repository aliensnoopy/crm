package com.yiyi.crm.utils;

import com.yiyi.crm.exceptions.ParamsException;

public class AssertUtil {
  public static void isTrue(Boolean flag, String msg) {
    if (flag) {
      throw  new ParamsException(msg);
    }
  }
}
