package edu.java.api.services.jdbc;

import edu.java.api.model.repository.jdbc.TgChatRepositoryImpl;
import edu.java.api.model.repository.jdbc.TgChatUrlRepositoryImpl;
import edu.java.api.services.interfaces.TgChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class JdbcTgChatServiceImpl implements TgChatService {
    private TgChatRepositoryImpl tgChatRepository;
    private TgChatUrlRepositoryImpl tgChatUrlRepository;

    @Autowired
    public JdbcTgChatServiceImpl(
        TgChatRepositoryImpl tgChatRepository,
        TgChatUrlRepositoryImpl tgChatUrlRepository
    ) {
        this.tgChatRepository = tgChatRepository;
        this.tgChatUrlRepository = tgChatUrlRepository;
    }

    public Mono<ResponseEntity<Void>> regNewTgChat(Long id) {
        return Mono.fromRunnable(() -> tgChatRepository.add(id))
            .then(Mono.just(ResponseEntity.ok().<Void>build()))
            .onErrorResume(error -> {
                return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
            });
    }

    public Mono<ResponseEntity<Void>> deleteTgChat(Long id) {
        return Mono.fromRunnable(() -> tgChatRepository.remove(id))
            .then(Mono.just(ResponseEntity.ok().<Void>build()))
            .onErrorResume(error -> {
                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
            });
    }
}
