package edu.java.api.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "CHAT")
@Entity
public class TgChatEntity {
    private Long id;
}
