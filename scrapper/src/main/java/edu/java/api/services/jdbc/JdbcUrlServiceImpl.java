package edu.java.api.services.jdbc;

import edu.java.api.model.TgChatUrl;
import edu.java.api.model.repository.jdbc.JdbcTgChatUrlRepositoryImpl;
import edu.java.api.model.repository.jdbc.JdbcUrlRepositoryImpl;
import edu.java.api.services.interfaces.UrlService;
import edu.java.generation.AddLinkRequest;
import edu.java.generation.LinkResponse;
import edu.java.generation.ListLinksResponse;
import edu.java.generation.RemoveLinkRequest;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JdbcUrlServiceImpl implements UrlService {

    private final JdbcTgChatUrlRepositoryImpl tgChatUrlRepository;
    private final JdbcUrlRepositoryImpl urlRepository;

    @Autowired
    public JdbcUrlServiceImpl(
        JdbcTgChatUrlRepositoryImpl tgChatUrlRepository,
        JdbcUrlRepositoryImpl urlRepository
    ) {
        this.tgChatUrlRepository = tgChatUrlRepository;
        this.urlRepository = urlRepository;
    }

    public LinkResponse addLinks(Long tgChatId, AddLinkRequest addLinkRequest) {

        String url = addLinkRequest.getLink().toString();

        if (Objects.isNull(urlRepository.getId(url))) {
            urlRepository.add(addLinkRequest.getLink().toString());
            log.debug("add " + url);
        }

        Long urlId = urlRepository.getId(url);

        TgChatUrl tgChatUrl = new TgChatUrl();
        tgChatUrl.setTgChatId(tgChatId);
        tgChatUrl.setUrlId(urlId);

        tgChatUrlRepository.add(tgChatUrl);
        LinkResponse linkResponse = new LinkResponse();
        return linkResponse.url(addLinkRequest.getLink()).id(tgChatId);

    }

    public ListLinksResponse getAllLinks(Long tgChatId) {

        List<LinkResponse> urls = tgChatUrlRepository.findByTgChatId(tgChatId).stream().map(urlId -> {
            LinkResponse linkResponse = new LinkResponse();
            return linkResponse.url(URI.create(urlRepository.findById(urlId))).id(tgChatId);
        }).collect(Collectors.toList());

        ListLinksResponse listLinksResponse = new ListLinksResponse();
        listLinksResponse.setLinks(urls);

        return listLinksResponse;
    }

    public LinkResponse deleteLink(Long tgChatId, RemoveLinkRequest removeLinkRequest) {
        TgChatUrl tgChatUrl = new TgChatUrl();

        Long urlId = urlRepository.getId(removeLinkRequest.getLink().toString());
        log.info("ID ссылки на удаление" + urlId);
        tgChatUrl.setUrlId(urlId);
        tgChatUrl.setTgChatId(tgChatId);

        tgChatUrlRepository.remove(tgChatUrl);

        return new LinkResponse().url(removeLinkRequest.getLink()).id(tgChatId);

    }
}
