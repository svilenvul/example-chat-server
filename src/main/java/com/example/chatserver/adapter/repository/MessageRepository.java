package com.example.chatserver.adapter.repository;

import com.example.chatserver.adapter.entities.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, String> {

}
