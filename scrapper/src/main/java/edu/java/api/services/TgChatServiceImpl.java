package edu.java.api.services;

import edu.java.api.model.repository.jdbc.TgChatRepositoryImpl;
import edu.java.api.model.repository.jdbc.TgChatUrlRepositoryImpl;
import edu.java.api.services.interfaces.TgChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TgChatServiceImpl implements TgChatService {
    private TgChatRepositoryImpl tgChatRepository;
    private TgChatUrlRepositoryImpl tgChatUrlRepository;

    @Autowired
    public TgChatServiceImpl(
        TgChatRepositoryImpl tgChatRepository,
        TgChatUrlRepositoryImpl tgChatUrlRepository
    ) {
        this.tgChatRepository = tgChatRepository;
        this.tgChatUrlRepository = tgChatUrlRepository;
    }

    public Mono<ResponseEntity<Void>> regNewTgChat(Long id) {
        tgChatRepository.add(id);
        return null;
    }

    public Mono<ResponseEntity<Void>> deleteTgChat(Long id) {
        tgChatRepository.remove(id);
        return null;
    }
}
