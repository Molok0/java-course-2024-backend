package edu.java.api.model.repository.jpa;

import edu.java.api.model.entity.TgChatEntity;
import edu.java.api.model.entity.TgChatUrlEntity;
import edu.java.api.model.entity.UrlEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTgChatUrlRepository extends JpaRepository<TgChatUrlEntity, Long> {

    List<TgChatUrlEntity> findAllByTgChat(TgChatEntity tgChat);

    List<TgChatUrlEntity> findAllByUrl(UrlEntity urlEntity);
}
