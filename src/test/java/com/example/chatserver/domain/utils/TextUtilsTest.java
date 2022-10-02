package com.example.chatserver.domain.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.chatserver.domain.util.TextUtils;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TextUtilsTest {

  @ValueSource(strings = {
      "asdad3d3",
      "1111",
      "12dfsdsd",
      "AAAAA5"
  })
  @ParameterizedTest(name = "Assert has numbers returns true if text contains numbers")
  void assertHasNumbersReturnsTrueIfTextHasNumbers(String text) {
    boolean result = TextUtils.hasNumbers(text);
    assertTrue(result);
  }


  @ValueSource(strings = {
      "",
      ".';]-!!#",
      "dfsdsd",
      "AAAAA"
  })
  @ParameterizedTest(name = "Assert has numbers returns false if text doesn't contain numbers")
  void assertHasNumbersReturnsFalseIfTextHasNoNumbers(String text) {
    boolean result = TextUtils.hasNumbers(text);
    assertFalse(result);
  }


  @MethodSource("assertLengthIsLessThanSource")
  @ParameterizedTest(name = "Assert length is more than: {1}")
  void assertLengthIsLessThan(String text, int length) {
    boolean result = TextUtils.lengthIsLessThen(text, length);
    assertTrue(result);
  }

  @MethodSource("assertLengthIsMoreThanSource")
  @ParameterizedTest(name = "Assert length is less than: {1}")
  void assertLengthIsMoreThan(String text, int length) {
    boolean result = TextUtils.lengthIsMoreThen(text, length);
    assertTrue(result);
  }

  public static Stream<Arguments> assertLengthIsLessThanSource() {
    return Stream.<Arguments>builder()
        .add(Arguments.of("MY first text message", 50))
        .add(Arguments.of("", 1))
        .add(Arguments.of("123456789", 10))
        .build();
  }
  public static Stream<Arguments> assertLengthIsMoreThanSource() {
    return Stream.<Arguments>builder()
        .add(Arguments.of("MY first text message", 10))
        .add(Arguments.of("", -1))
        .add(Arguments.of("123456789", 8))
        .build();
  }
}
