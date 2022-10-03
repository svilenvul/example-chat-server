package com.example.chatserver.data.entities;

import com.example.chatserver.domain.mapping.Mapper;
import com.example.chatserver.domain.models.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper implements Mapper<Message, MessageEntity> {

  @Override
  public MessageEntity map(Message message) {
    MessageEntity entity = new MessageEntity();
    entity.setPayload(message.getPayload());
    entity.setType(message.getMessageType().name());
    return entity;
  }
}
