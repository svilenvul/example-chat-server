package com.example.chatserver.data.validation;

import com.example.chatserver.domain.models.Message;
import com.example.chatserver.domain.models.MessageType;
import com.example.chatserver.domain.util.TextUtils;
import com.example.chatserver.domain.validation.MessageValidator;
import org.springframework.stereotype.Component;

@Component
public class TextMessageValidator implements MessageValidator {

  public static final int MIN_LENGTH = 1;
  public static final int MAX_LENGTH = 160;

  @Override
  public MessageType getType() {
    return MessageType.TEXT;
  }

  @Override
  public boolean isValid(Message message) {
    MessageType messageType = message.getMessageType();
    String payload = message.getPayload();

    if (messageType != getType()) {
      return false;
    }

    if (payload == null) {
      return false;
    }

    if (TextUtils.lengthIsMoreThen(payload, MAX_LENGTH)) {
      return false;
    }

    if (TextUtils.lengthIsLessThen(payload, MIN_LENGTH)) {
      return false;
    }

    return true;
  }
}
