package edu.java.api.controllers;

import edu.java.api.services.TgChatServiceImpl;
import edu.java.generation.TgChatApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class TgChatController implements TgChatApi {
    private TgChatServiceImpl tgChatService;

    @Autowired
    public TgChatController(TgChatServiceImpl tgChatService) {
        this.tgChatService = tgChatService;
    }

    @Override
    public Mono<ResponseEntity<Void>> tgChatIdDelete(Long id) {
        return tgChatService.deleteTgChat(id);
    }

    @Override
    public Mono<ResponseEntity<Void>> tgChatIdPost(Long id) {
        return tgChatService.regNewTgChat(id);
    }
}
