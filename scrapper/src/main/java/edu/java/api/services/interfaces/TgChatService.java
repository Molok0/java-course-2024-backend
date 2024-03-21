package edu.java.api.services.interfaces;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface TgChatService {
    Mono<ResponseEntity<Void>> regNewTgChat(Long id);

    Mono<ResponseEntity<Void>> deleteTgChat(Long id);
}
