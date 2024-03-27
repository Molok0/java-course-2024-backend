package edu.java.api.model.repository.interfaces;

import edu.java.api.model.TgChatUrl;
import java.util.List;

public interface TgChatUrlRepository {
    void add(TgChatUrl tgChatUrl);

    void remove(TgChatUrl tgChatUrl);

    List<TgChatUrl> findAll();

    List<Long> findByTgChatId(Long id);

    void removeByTgChatId(Long id);
}
