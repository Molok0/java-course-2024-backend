package edu.java.api.services.jdbc;

import edu.java.api.model.TgChatUrl;
import edu.java.api.model.repository.jdbc.TgChatRepositoryImpl;
import edu.java.api.model.repository.jdbc.TgChatUrlRepositoryImpl;
import edu.java.api.model.repository.jdbc.UrlRepositoryImpl;
import edu.java.api.services.interfaces.UrlService;
import edu.java.generation.AddLinkRequest;
import edu.java.generation.LinkResponse;
import edu.java.generation.ListLinksResponse;
import edu.java.generation.RemoveLinkRequest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class JdbcUrlServiceImpl implements UrlService {

    private final TgChatUrlRepositoryImpl tgChatUrlRepository;
    private final UrlRepositoryImpl urlRepository;

    @Autowired
    public JdbcUrlServiceImpl(
        TgChatUrlRepositoryImpl tgChatUrlRepository,
        UrlRepositoryImpl urlRepository
    ) {
        this.tgChatUrlRepository = tgChatUrlRepository;
        this.urlRepository = urlRepository;
    }

    public Mono<ResponseEntity<LinkResponse>> addLinks(Long tgChatId, Mono<AddLinkRequest> addLinkRequest) {
        return addLinkRequest.map(addLinkRequest1 -> {

            urlRepository.add(addLinkRequest1.toString());

            var urlId = urlRepository.findAll().getFirst();
            TgChatUrl tgChatUrl = new TgChatUrl();
            tgChatUrl.setTgChatId(tgChatId);
            tgChatUrl.setUrlId(urlId.getId());

            tgChatUrlRepository.add(tgChatUrl);

            LinkResponse linkResponse = new LinkResponse();
            linkResponse.url(addLinkRequest1.getLink());
            linkResponse.id(tgChatId);

            return ResponseEntity.ok(linkResponse);
        });
    }

    public Mono<ResponseEntity<ListLinksResponse>> getAllLinks(Long tgChatId) {

        List<LinkResponse> urls = tgChatUrlRepository.findByTgChatId(tgChatId).stream().map(url -> {
            LinkResponse linkResponse = new LinkResponse();
            linkResponse.id(tgChatId);
            linkResponse.url(URI.create(url));
            return linkResponse;
        }).toList();

        ListLinksResponse listLinksResponse = new ListLinksResponse();
        listLinksResponse.setLinks(urls);

        return Mono.just(ResponseEntity.ok(listLinksResponse));
    }

    public Mono<ResponseEntity<LinkResponse>> deleteLink(Long tgChatId, Mono<RemoveLinkRequest> removeLinkRequest) {
        return null;
    }
}
