package edu.java.api.model.entity;

import edu.java.api.model.entity.Key.TgChatUrlKey;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "CHAT_URL")
@Entity
@IdClass(TgChatUrlKey.class)
public class TgChatUrlEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private TgChatEntity tgChat;

    @Id
    @ManyToOne
    @JoinColumn(name = "url_id")
    private UrlEntity url;

}
