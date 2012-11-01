package edu.bsu.registrator.util;

import java.security.MessageDigest;
import java.util.Date;

/**
 * @author Eugene Pavlovsky
 * @since 01.11.12
 */
public class SynchronizedTokenUtil {

  private static final String DEFAULT_TOKEN = "token";

  public static String generateToken() {
    try {
      MessageDigest digest = MessageDigest.getInstance("MD5");
      digest.update(new Date().toString().getBytes());
      byte messageDigest[] = digest.digest();
      StringBuilder hexString = new StringBuilder();
      for (byte b : messageDigest) {
        hexString.append(Integer.toHexString(0xFF & b));
      }
      return hexString.toString();
    } catch (Exception e) {
      return DEFAULT_TOKEN;
    }
  }
}
