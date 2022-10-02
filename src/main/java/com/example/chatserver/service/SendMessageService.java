package com.example.chatserver.service;

import com.example.chatserver.adapter.entities.MessageEntity;
import com.example.chatserver.adapter.repository.MessageRepository;
import com.example.chatserver.domain.exception.MessageValidationException;
import com.example.chatserver.domain.exception.ValidatorNotFoundException;
import com.example.chatserver.domain.mapping.Mapper;
import com.example.chatserver.domain.models.Message;
import com.example.chatserver.domain.validation.MessageValidator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMessageService {

  private final Logger logger = LoggerFactory.getLogger(SendMessageService.class.getName());
  private Mapper<Message, MessageEntity> mapper;

  private MessageRepository messageRepository;

  private List<MessageValidator> messageValidatorList;
  private Map<String, MessageValidator> messageValidatorMap = new HashMap<>();

  @Autowired
  public SendMessageService(MessageRepository messageRepository, Mapper<Message, MessageEntity> mapper,
      List<MessageValidator> messageValidatorList) {
    this.messageValidatorList = messageValidatorList;
    this.mapper = mapper;
    this.messageRepository = messageRepository;
  }

  @PostConstruct
  void init() {
    for (MessageValidator messageValidator : messageValidatorList) {
      messageValidatorMap.put(messageValidator.getType().name(), messageValidator);
    }
  }

  public void sendMessage(Message message) throws MessageValidationException, ValidatorNotFoundException {
    logger.debug("Validating message with type: {}", message.getMessageType());

    MessageValidator validator = messageValidatorMap.get(message.getMessageType().name());

    if (validator == null) {
      throw new ValidatorNotFoundException();
    }

    if (!validator.isValid(message)) {
      throw new MessageValidationException();
    }

    mapper.map(message);
    logger.debug("Saving message with type: {} in db", message.getMessageType());

    messageRepository.save(mapper.map(message));
  }
}
