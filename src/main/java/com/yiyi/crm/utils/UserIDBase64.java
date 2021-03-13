package com.yiyi.crm.utils;

import java.util.Base64;
import org.apache.commons.lang3.StringUtils;

public class UserIDBase64 {
  /**
   * Decode userId.
   * @param encodedUserID encrypted user id.
   * @return
   */
	public static Integer decoderUserID(String encodedUserID) {
		if (StringUtils.isBlank(encodedUserID)) {
			return null;
		}
		try {
			String reversedString = new StringBuffer(encodedUserID).reverse().toString();
			String base64String = reversedString.replaceAll("#", "=");
			int userIDPos = base64String.indexOf("==") + 6;
			String realBase64UserID = base64String.substring(userIDPos);
			String base64Encoded = new String(Base64.getDecoder().decode(realBase64UserID.getBytes()));
			return Integer.parseInt(base64Encoded);
		} catch (Exception e) {
			return null;
		}
	}

  /**
   * Encode user id.
   * @param userID
   * @return
   */
	public static String encoderUserID(Integer userID){
		String base64UserIDEncoded =
        Base64.getEncoder().encodeToString(String.valueOf(userID).getBytes());
		String currentStringBase64Encoded =
        Base64.getEncoder().encodeToString(String.valueOf(System.currentTimeMillis()).getBytes());
		String keyString = currentStringBase64Encoded  
				+ currentStringBase64Encoded.substring(4, 8) + base64UserIDEncoded;
		byte[] codeBytes = keyString.getBytes();
		byte[] orderedBytes = new byte[codeBytes.length];
		for(int i = 0; i< codeBytes.length; i++){
			orderedBytes[i] = codeBytes[codeBytes.length - i - 1];
		}
		return new String(orderedBytes).replaceAll("=", "#");
	}

	public static void main(String[] args) {
		System.out.println(encoderUserID(20));
		System.out.println(decoderUserID("#AjMzgjM##QN1AjN4gTOzgjM3UTM"));
	}
}