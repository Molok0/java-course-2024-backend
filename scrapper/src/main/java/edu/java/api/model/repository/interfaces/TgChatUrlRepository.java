package edu.java.api.model.repository.interfaces;

import edu.java.api.model.TgChat;
import edu.java.api.model.TgChatUrl;
import java.util.List;

public interface TgChatUrlRepository {
    void add(TgChatUrl tgChatUrl);

    void remove(TgChatUrl tgChatUrl);

    List<TgChat> findAll();
}
