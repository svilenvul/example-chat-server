package com.example.chatserver.domain.validation;

import com.example.chatserver.domain.models.MessageType;
import com.example.chatserver.domain.models.Message;

public interface MessageValidator {

  MessageType getType();
  boolean isValid(Message message);
}
