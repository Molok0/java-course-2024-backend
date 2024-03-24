package edu.java.api.services.interfaces;

import edu.java.generation.AddLinkRequest;
import edu.java.generation.LinkResponse;
import edu.java.generation.ListLinksResponse;
import edu.java.generation.RemoveLinkRequest;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface UrlService {
    Mono<ResponseEntity<LinkResponse>> addLinks(Long tgChatId, Mono<AddLinkRequest> addLinkRequest);

    Mono<ResponseEntity<ListLinksResponse>> getAllLinks(Long tgChatId);

    Mono<ResponseEntity<LinkResponse>> deleteLink(Long tgChatId, Mono<RemoveLinkRequest> removeLinkRequest);
}
