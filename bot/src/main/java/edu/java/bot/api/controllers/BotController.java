package edu.java.bot.api.controllers;

import edu.java.bot.LinkUpdate;
import edu.java.bot.UpdatesApi;
import edu.java.bot.api.services.BotService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
@Slf4j
@RestController
@RequiredArgsConstructor
public class BotController implements UpdatesApi {
    private BotService botService;

    @Autowired
    public BotController(BotService botService) {
        this.botService = botService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return UpdatesApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> updatesPost(LinkUpdate linkUpdate) {
        log.info("Получил обновление");
        return ResponseEntity.status(botService.sendUpdate(linkUpdate)).build();
    }
}
