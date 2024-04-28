package edu.java.domain.repository.interfaces;

import edu.java.domain.TgChatUrl;
import java.util.List;

public interface TgChatUrlRepository {
    void add(TgChatUrl tgChatUrl);

    void remove(TgChatUrl tgChatUrl);

    List<TgChatUrl> findAll();

    List<Long> findByTgChatId(Long id);

    List<Long> findByUrlId(Long id);

}
