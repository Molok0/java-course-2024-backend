package edu.java.api.controllers;

import edu.java.api.services.interfaces.TgChatService;
import edu.java.generation.TgChatApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class TgChatController implements TgChatApi {
    private TgChatService tgChatService;

    @Autowired
    public TgChatController(TgChatService tgChatService) {
        this.tgChatService = tgChatService;
    }

    @Override
    public Mono<ResponseEntity<Void>> tgChatIdDelete(Long id) {
        tgChatService.deleteTgChat(id);
        return Mono.just(ResponseEntity.ok().build());
    }

    @Override
    public Mono<ResponseEntity<Void>> tgChatIdPost(Long id) {
        tgChatService.regNewTgChat(id);
        return Mono.just(ResponseEntity.ok().build());
    }
}
