package com.example.chatserver.adapter.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.chatserver.domain.models.MessageType;
import com.example.chatserver.domain.models.Message;
import com.example.chatserver.domain.validation.MessageValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
class TextMessageValidatorTest {
  private MessageValidator validator = new TextMessageValidator();


  @ValueSource(
      strings = {
          "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient.",
          "!",
          "My 1st cool message!",
          "Can! Fa1l i!!"
      }
  )
  @ParameterizedTest(name = "Assert message is valid: {0}")
  void testValidMessages(String payload) {
    Message message = new Message(payload, MessageType.TEXT);
    boolean result = validator.isValid(message);
    assertTrue(result);
  }

  @ValueSource(
      strings = {
          "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient m",
          "",
      }
  )
  @ParameterizedTest(name = "Assert message is invalid: {0}")
  void testInvalidMessages(String payload) {
    Message message = new Message(payload, MessageType.TEXT);
    boolean result = validator.isValid(message);
    assertFalse(result);
  }
}
