package edu.java.api.services.jdbc;

import edu.java.api.model.repository.jdbc.TgChatRepositoryImpl;
import edu.java.api.model.repository.jdbc.TgChatUrlRepositoryImpl;
import edu.java.api.services.interfaces.TgChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JdbcTgChatServiceImpl implements TgChatService {
    private final TgChatRepositoryImpl tgChatRepository;
    private final TgChatUrlRepositoryImpl tgChatUrlRepository;

    @Autowired
    public JdbcTgChatServiceImpl(
        TgChatRepositoryImpl tgChatRepository,
        TgChatUrlRepositoryImpl tgChatUrlRepository
    ) {
        this.tgChatRepository = tgChatRepository;
        this.tgChatUrlRepository = tgChatUrlRepository;
    }

    public void regNewTgChat(Long id) {
        tgChatRepository.add(id);
    }

    public void deleteTgChat(Long id) {
        tgChatRepository.remove(id);
    }
}
