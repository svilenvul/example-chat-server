package com.example.chatserver.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.example.chatserver.data.entities.MessageMapper;
import com.example.chatserver.domain.models.MessageType;
import com.example.chatserver.data.repository.MessageRepository;
import com.example.chatserver.domain.exception.MessageValidationException;
import com.example.chatserver.domain.exception.ValidatorNotFoundException;
import com.example.chatserver.domain.models.Message;
import com.example.chatserver.domain.validation.MessageValidator;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SendMessageServiceTest {

  @Mock
  private MessageValidator messageValidator;
  @Mock
  private MessageRepository messageRepositoryMock;
  private SendMessageService sendMessageService;


  @DisplayName("Assert success when validation and repository layer passes")
  @Test
  void assertSuccess() throws MessageValidationException, ValidatorNotFoundException {
    sendMessageService = new SendMessageService(
        messageRepositoryMock,
        new MessageMapper(),
        List.of(messageValidator)
    );
    when(messageValidator.getType()).thenReturn(MessageType.TEXT);
    when(messageValidator.isValid(any(Message.class))).thenReturn(true);
    sendMessageService.init();


    String body = "My first text message";
    MessageType type = MessageType.TEXT;
    Message message = new Message(body, type);

    sendMessageService.sendMessage(message);

    verify(messageRepositoryMock).save(any());
  }


  @DisplayName("Assert exception is thrown when validator is missing")
  @Test
  void assertFailureWhenValidatorMissing() {
    sendMessageService = new SendMessageService(
        messageRepositoryMock,
        null,
        Collections.emptyList()
    );
    sendMessageService.init();

    String body = "My first text message";
    MessageType type = MessageType.TEXT;
    Message message = new Message(body, type);

    assertThrows(ValidatorNotFoundException.class, () ->
        sendMessageService.sendMessage(message));

    verifyNoInteractions(messageRepositoryMock);
  }

  @DisplayName("Assert exception is thrown when validator is missing")
  @Test
  void assertFailureWhenValidatorFails() {
    sendMessageService = new SendMessageService(
        messageRepositoryMock,
        null,
        List.of(messageValidator)
    );
    when(messageValidator.getType()).thenReturn(MessageType.TEXT);
    when(messageValidator.isValid(any(Message.class))).thenReturn(false);
    sendMessageService.init();


    String body = "My first text message";
    MessageType type = MessageType.TEXT;
    Message message = new Message(body, type);

    assertThrows(MessageValidationException.class, () ->
        sendMessageService.sendMessage(message));

    verifyNoInteractions(messageRepositoryMock);
  }

}
