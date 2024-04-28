package edu.java.service.services.jdbc;

import edu.java.domain.repository.interfaces.TgChatRepository;
import edu.java.service.services.interfaces.TgChatService;

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
