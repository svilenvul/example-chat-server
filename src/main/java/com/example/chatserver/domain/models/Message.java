package com.example.chatserver.domain.models;

public class Message {
  private final String payload;
  private final MessageType messageType;

  public Message (String payload, MessageType messageType) {
    this.payload = payload;
    this.messageType = messageType;
  }

  public String getPayload() {
    return payload;
  }

  public MessageType getMessageType() {
    return messageType;
  }
}
