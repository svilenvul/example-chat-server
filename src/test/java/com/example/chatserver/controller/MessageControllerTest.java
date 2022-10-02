package com.example.chatserver.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import com.example.chatserver.domain.exception.MessageValidationException;
import com.example.chatserver.domain.exception.ValidatorNotFoundException;
import com.example.chatserver.domain.models.Message;
import com.example.chatserver.service.SendMessageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
class MessageControllerTest {

  @Mock
  SendMessageService sendMessageService;

  @InjectMocks
  MessageController messageController;

  @DisplayName("Assert unknown type returns not found")
  @Test
  void assertUnknownTypeReturnsNotFound() {
    String type = "unknown";
    String payload = "Cool!";

    var response = messageController.handleMessage(type, payload);

    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());
  }

  @DisplayName("Assert unknown type returns not found")
  @Test
  void assertKnownMessageReturnsCreated() throws MessageValidationException, ValidatorNotFoundException {
    String type = "send_text";
    String payload = "Cool!";
    doNothing().when(sendMessageService).sendMessage(any(Message.class));

    var response = messageController.handleMessage(type, payload);

    assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
  }

  @DisplayName("Assert known type with validation exception returns precondition failed")
  @Test
  void assertPreconditionFailed() throws MessageValidationException, ValidatorNotFoundException {
    String type = "send_text";
    String payload = "Cool!";
    doThrow(new MessageValidationException()).when(sendMessageService).sendMessage(any(Message.class));

    var response = messageController.handleMessage(type, payload);

    assertEquals(HttpStatus.PRECONDITION_FAILED.value(), response.getStatusCode().value());
  }

  @DisplayName("Assert missing validator exception returns internal server error")
  @Test
  void assertMissingValidatorReturnsInternalServerError() throws MessageValidationException, ValidatorNotFoundException {
    String type = "send_text";
    String payload = "Cool!";
    doThrow(new ValidatorNotFoundException()).when(sendMessageService).sendMessage(any(Message.class));

    var response = messageController.handleMessage(type, payload);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCode().value());
  }

}
