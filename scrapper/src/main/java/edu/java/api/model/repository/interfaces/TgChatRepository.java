package edu.java.api.model.repository.interfaces;

import edu.java.api.model.TgChat;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface TgChatRepository {
    void add(TgChat tgChat);

    void remove(TgChat tgChat);

    List<TgChat> findAll();
}
