package edu.java.api.services.jpa;

import edu.java.api.services.interfaces.TgChatService;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public class JpaTgChatServiceImpl implements TgChatService {
    @Override
    public Mono<ResponseEntity<Void>> regNewTgChat(Long id) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteTgChat(Long id) {
        return null;
    }
}
