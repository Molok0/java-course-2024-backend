package edu.java.api.services.jdbc;

import edu.java.api.model.TgChatUrl;
import edu.java.api.model.repository.jdbc.TgChatUrlRepositoryImpl;
import edu.java.api.model.repository.jdbc.UrlRepositoryImpl;
import edu.java.api.services.interfaces.UrlService;
import edu.java.generation.AddLinkRequest;
import edu.java.generation.LinkResponse;
import edu.java.generation.ListLinksResponse;
import edu.java.generation.RemoveLinkRequest;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
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

    public Mono<ResponseEntity<LinkResponse>> addLinks(Long tgChatId, AddLinkRequest addLinkRequest) {


            urlRepository.add(addLinkRequest.getLink().toString());

            Long urlId = urlRepository.getId(addLinkRequest.getLink().toString());

            TgChatUrl tgChatUrl = new TgChatUrl();
            tgChatUrl.setTgChatId(tgChatId);
            tgChatUrl.setUrlId(urlId);

            tgChatUrlRepository.add(tgChatUrl);
            LinkResponse linkResponse = new LinkResponse();
            return Mono.just(ResponseEntity.ok(linkResponse.url(addLinkRequest.getLink()).id(tgChatId)));

    }

    public Mono<ResponseEntity<ListLinksResponse>> getAllLinks(Long tgChatId) {

        List<LinkResponse> urls = tgChatUrlRepository.findByTgChatId(tgChatId).stream().map(urlId -> {
            LinkResponse linkResponse = new LinkResponse();
            return linkResponse.url(URI.create(urlRepository.findById(urlId))).id(tgChatId);
        }).collect(Collectors.toList());

        ListLinksResponse listLinksResponse = new ListLinksResponse();
        listLinksResponse.setLinks(urls);

        return Mono.just(ResponseEntity.ok(listLinksResponse));
    }

    public Mono<ResponseEntity<LinkResponse>> deleteLink(Long tgChatId, Mono<RemoveLinkRequest> removeLinkRequest) {
        return removeLinkRequest.map(request -> {
            TgChatUrl tgChatUrl = new TgChatUrl();
            Long urlId = urlRepository.getId(request.getLink().toString());
            tgChatUrl.setUrlId(urlId);
            tgChatUrl.setTgChatId(tgChatId);
            tgChatUrlRepository.remove(tgChatUrl);
            return ResponseEntity.ok(new LinkResponse().url(request.getLink()).id(tgChatId));
        });
    }
}
