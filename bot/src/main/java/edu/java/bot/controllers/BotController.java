package edu.java.bot.controllers;

import edu.java.bot.LinkUpdate;
import edu.java.bot.UpdatesApi;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

@RestController
@RequiredArgsConstructor
public class BotController implements UpdatesApi {
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return UpdatesApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> updatesPost(LinkUpdate linkUpdate) {
        return UpdatesApi.super.updatesPost(linkUpdate);
    }
}
