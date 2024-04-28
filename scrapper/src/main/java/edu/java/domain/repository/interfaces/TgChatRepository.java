package edu.java.domain.repository.interfaces;

import edu.java.domain.TgChat;
import java.util.List;

public interface TgChatRepository {
    void add(Long id);

    void remove(Long id);

    List<TgChat> findAll();
}
