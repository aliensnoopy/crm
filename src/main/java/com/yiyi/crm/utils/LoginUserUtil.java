package com.yiyi.crm.utils;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public class LoginUserUtil {
  /**
   * Get url from cookie.
   * @param request
   * @return
   */
  public static int releaseUserIdFromCookie(HttpServletRequest request) {
    String userIdString = CookieUtil.getCookieValue(request, "encryptedUserId");
    if (StringUtils.isBlank(userIdString)) {
      return 0;
    }
    Integer userId = UserIDBase64.decoderUserID(userIdString);
    return userId;
  }
}
