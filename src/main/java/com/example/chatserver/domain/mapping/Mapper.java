package com.example.chatserver.domain.mapping;

public interface Mapper <I, O>{
  O map(I input);

}
