package com.example.chatserver.controller;

import com.example.chatserver.domain.models.MessageType;
import com.example.chatserver.domain.exception.MessageValidationException;
import com.example.chatserver.domain.exception.ValidatorNotFoundException;
import com.example.chatserver.domain.models.Message;
import com.example.chatserver.service.SendMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

  private final Logger logger = LoggerFactory.getLogger(MessageController.class.getName());

  @Autowired
  private SendMessageService sendMessageService;

  @PostMapping("/messages/{type}")
  ResponseEntity<Void> handleMessage(@PathVariable String type, @RequestBody String payload) {
    logger.debug("Received message with type: {}", type);
    type = type.replace("send_", "");

    try {
      MessageType messageType = MessageType.create(type);
      if (messageType == null) {
        return ResponseEntity.notFound().build();

      }
      Message message = new Message(payload, messageType);
      sendMessageService.sendMessage(message);

    } catch (ValidatorNotFoundException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    } catch (MessageValidationException e) {
      // precondition failed
      return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
    }

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }


}
