package edu.java.domain.repository.jpa;

import edu.java.domain.entity.TgChatEntity;
import edu.java.domain.entity.TgChatUrlEntity;
import edu.java.domain.entity.UrlEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTgChatUrlRepository extends JpaRepository<TgChatUrlEntity, Long> {

    List<TgChatUrlEntity> findAllByTgChat(TgChatEntity tgChat);

    List<TgChatUrlEntity> findAllByUrl(UrlEntity urlEntity);
}
