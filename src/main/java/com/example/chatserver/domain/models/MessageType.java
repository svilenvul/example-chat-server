package com.example.chatserver.domain.models;

public enum MessageType {
  TEXT("text"),
  EMOTION("emotion");

  private final String type;

  MessageType(String type) {
    this.type = type;
  }

  public static MessageType create(String type) {
    for (MessageType value : MessageType.values()) {
      if (value.type.equals(type)) {
        return value;
      }
    }
    return null;
  }


}
