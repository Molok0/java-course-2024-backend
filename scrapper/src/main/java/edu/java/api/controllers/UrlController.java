package edu.java.api.controllers;

import edu.java.api.services.interfaces.UrlService;
import edu.java.generation.AddLinkRequest;
import edu.java.generation.LinkResponse;
import edu.java.generation.LinksApi;
import edu.java.generation.ListLinksResponse;
import edu.java.generation.RemoveLinkRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class UrlController implements LinksApi {
    private UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
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
        return Mono.just(ResponseEntity.ok(urlService.addLinks(tgChatId, addLinkRequest)));
    }
}
