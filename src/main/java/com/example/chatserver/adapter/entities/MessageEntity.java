package com.example.chatserver.adapter.entities;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "message")
@EntityListeners(AuditingEntityListener.class)
public class MessageEntity {

  @Id
  @Column(name = "id")
  @Type(type = "uuid-char")
  private UUID uuid  = UUID.randomUUID();

  private String payload;

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  private String type;



}
