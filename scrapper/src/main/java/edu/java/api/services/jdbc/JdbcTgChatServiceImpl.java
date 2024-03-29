package edu.java.api.services.jdbc;

import edu.java.api.model.repository.jdbc.JdbcTgChatRepositoryImpl;
import edu.java.api.model.repository.jdbc.JdbcTgChatUrlRepositoryImpl;
import edu.java.api.services.interfaces.TgChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JdbcTgChatServiceImpl implements TgChatService {
    private final JdbcTgChatRepositoryImpl tgChatRepository;
    private final JdbcTgChatUrlRepositoryImpl tgChatUrlRepository;

    @Autowired
    public JdbcTgChatServiceImpl(
        JdbcTgChatRepositoryImpl tgChatRepository,
        JdbcTgChatUrlRepositoryImpl tgChatUrlRepository
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
