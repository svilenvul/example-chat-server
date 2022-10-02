package com.example.chatserver.domain.util;

public class TextUtils {

  private TextUtils() {

  }
  public static boolean hasNumbers(String text) {
    return text != null && text.matches(".*\\d.*");
  }

  public static boolean lengthIsLessThen(String text, int characters) {
    return text != null && text.length() < characters;
  }

  public static boolean lengthIsMoreThen(String text, int characters) {
    return text != null && text.length() > characters;
  }
}
