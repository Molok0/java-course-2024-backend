package edu.java.api.services;

import edu.java.api.model.repository.jdbc.TgChatRepositoryImpl;
import edu.java.api.model.repository.jdbc.TgChatUrlRepositoryImpl;
import edu.java.api.model.repository.jdbc.UrlRepositoryImpl;
import edu.java.api.services.interfaces.UrlService;
import edu.java.generation.AddLinkRequest;
import edu.java.generation.LinkResponse;
import edu.java.generation.ListLinksResponse;
import edu.java.generation.RemoveLinkRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UrlServiceImpl implements UrlService {

    private TgChatUrlRepositoryImpl tgChatUrlRepository;
    private TgChatRepositoryImpl tgChatRepository;
    private UrlRepositoryImpl urlRepository;

    @Autowired
    public UrlServiceImpl(
        TgChatUrlRepositoryImpl tgChatUrlRepository,
        TgChatRepositoryImpl tgChatRepository,
        UrlRepositoryImpl urlRepository
    ) {
        this.tgChatUrlRepository = tgChatUrlRepository;
        this.tgChatRepository = tgChatRepository;
        this.urlRepository = urlRepository;
    }

    public Mono<ResponseEntity<LinkResponse>> addLinks(Long tgChatId, Mono<AddLinkRequest> addLinkRequest) {
        return null;
    }

    public Mono<ResponseEntity<ListLinksResponse>> getAllLinks(Long tgChatId) {
        return null;
    }

    public Mono<ResponseEntity<LinkResponse>> deleteLink(Long tgChatId, Mono<RemoveLinkRequest> removeLinkRequest) {
        return null;
    }
}
