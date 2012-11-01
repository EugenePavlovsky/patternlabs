package edu.bsu.registrator.util;

/**
 * @author Eugene Pavlovsky
 * @since 01.11.12
 */
public class DataConversionUtil {

  public static Long convertFromStringToLong(String number) {
    Long result;
    try {
      result = Long.parseLong(number);
    } catch (Exception e) {
      result = null;
    }
    return result;
  }
}
