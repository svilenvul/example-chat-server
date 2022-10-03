package com.example.chatserver.data.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.chatserver.domain.models.MessageType;
import com.example.chatserver.domain.models.Message;
import com.example.chatserver.domain.validation.MessageValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EmotionMessageValidatorTest {

  private MessageValidator validator = new EmotionMessageValidator();


  @ValueSource(
      strings = {
          "Hi",
          "Good mood",
          "!!!!!",
          "ASDFGZXCVB"
      }
  )
  @ParameterizedTest(name = "Assert message is valid: {0}")
  void testValidMessages(String payload) {
    Message message = new Message(payload, MessageType.EMOTION);
    boolean result = validator.isValid(message);
    assertTrue(result);
  }

  @ValueSource(
      strings = {
          "A",
          "Good mood!!!",
          "1!!!!",
          "AS2356CVB",
          ""
      }
  )
  @ParameterizedTest(name = "Assert message is invalid: {0}")
  void testInvalidMessages(String payload) {
    Message message = new Message(payload, MessageType.EMOTION);
    boolean result = validator.isValid(message);
    assertFalse(result);
  }
}
