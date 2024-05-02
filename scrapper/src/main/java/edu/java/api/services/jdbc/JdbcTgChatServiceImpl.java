package edu.java.api.services.jdbc;

import edu.java.api.model.repository.interfaces.TgChatRepository;
import edu.java.api.services.interfaces.TgChatService;

public class JdbcTgChatServiceImpl implements TgChatService {
    private final TgChatRepository tgChatRepository;

    public JdbcTgChatServiceImpl(
        TgChatRepository tgChatRepository
    ) {
        this.tgChatRepository = tgChatRepository;
    }

    public void regNewTgChat(Long id) {
        tgChatRepository.add(id);
    }

    public void deleteTgChat(Long id) {
        tgChatRepository.remove(id);
    }
}
