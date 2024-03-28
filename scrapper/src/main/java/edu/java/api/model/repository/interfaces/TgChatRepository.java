package edu.java.api.model.repository.interfaces;

import edu.java.api.model.TgChat;
import java.util.List;

public interface TgChatRepository {
    void add(Long id);

    void remove(Long id);

    List<TgChat> findAll();
}
