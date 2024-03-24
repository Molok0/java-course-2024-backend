package edu.java.api.services.jpa;

import edu.java.api.services.interfaces.UrlService;
import edu.java.generation.AddLinkRequest;
import edu.java.generation.LinkResponse;
import edu.java.generation.ListLinksResponse;
import edu.java.generation.RemoveLinkRequest;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public class JpaUrlServiceImpl implements UrlService {
    @Override
    public Mono<ResponseEntity<LinkResponse>> addLinks(Long tgChatId, Mono<AddLinkRequest> addLinkRequest) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<ListLinksResponse>> getAllLinks(Long tgChatId) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<LinkResponse>> deleteLink(Long tgChatId, Mono<RemoveLinkRequest> removeLinkRequest) {
        return null;
    }
}
