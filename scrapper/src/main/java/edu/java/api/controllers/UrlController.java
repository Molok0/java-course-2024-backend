package edu.java.api.controllers;

import edu.java.api.services.jdbc.JdbcUrlServiceImpl;
import edu.java.generation.AddLinkRequest;
import edu.java.generation.LinkResponse;
import edu.java.generation.LinksApi;
import edu.java.generation.ListLinksResponse;
import edu.java.generation.RemoveLinkRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UrlController implements LinksApi {
    private JdbcUrlServiceImpl urlService;

    @Autowired
    public UrlController(JdbcUrlServiceImpl urlService) {
        this.urlService = urlService;
    }

    @Override
    public Mono<ResponseEntity<LinkResponse>> linksDelete(
        Long tgChatId,
        RemoveLinkRequest removeLinkRequest
    ) {
        return Mono.just(ResponseEntity.ok(urlService.deleteLink(tgChatId, removeLinkRequest)));
    }

    @Override
    public Mono<ResponseEntity<ListLinksResponse>> linksGet(Long tgChatId) {
        return Mono.just(ResponseEntity.ok(urlService.getAllLinks(tgChatId)));
    }

    @Override
    public Mono<ResponseEntity<LinkResponse>> linksPost(
        Long tgChatId,
        AddLinkRequest addLinkRequest
    ) {
        log.info(String.valueOf(tgChatId));
        return Mono.just(ResponseEntity.ok(urlService.addLinks(tgChatId, addLinkRequest)));
    }
}
