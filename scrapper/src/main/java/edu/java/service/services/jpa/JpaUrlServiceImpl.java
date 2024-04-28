package edu.java.service.services.jpa;

import edu.java.domain.entity.TgChatEntity;
import edu.java.domain.entity.TgChatUrlEntity;
import edu.java.domain.entity.UrlEntity;
import edu.java.domain.generation.AddLinkRequest;
import edu.java.domain.generation.LinkResponse;
import edu.java.domain.generation.ListLinksResponse;
import edu.java.domain.generation.RemoveLinkRequest;
import edu.java.domain.repository.jpa.JpaTgChatRepository;
import edu.java.domain.repository.jpa.JpaTgChatUrlRepository;
import edu.java.domain.repository.jpa.JpaUrlRepository;
import edu.java.service.services.interfaces.UrlService;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JpaUrlServiceImpl implements UrlService {

    private final JpaUrlRepository jpaUrlRepository;
    private final JpaTgChatUrlRepository jpaTgChatUrlRepository;
    private final JpaTgChatRepository jpaTgChatRepository;

    public JpaUrlServiceImpl(
        JpaUrlRepository jpaUrlRepository,
        JpaTgChatUrlRepository jpaTgChatUrlRepository,
        JpaTgChatRepository jpaTgChatRepository
    ) {
        this.jpaUrlRepository = jpaUrlRepository;
        this.jpaTgChatUrlRepository = jpaTgChatUrlRepository;
        this.jpaTgChatRepository = jpaTgChatRepository;
    }

    @Override
    public LinkResponse addLinks(Long tgChatId, AddLinkRequest addLinkRequest) {
        String url = addLinkRequest.getLink().toString();
        var id = jpaUrlRepository.findIdByUrl(url);
        if (Objects.isNull(id)) {

            UrlEntity urlEntity = new UrlEntity();
            urlEntity.setUrl(addLinkRequest.getLink().toString());
            urlEntity.setLastCheck(OffsetDateTime.now());
            urlEntity.setLastChange(OffsetDateTime.now());

            jpaUrlRepository.saveAndFlush(urlEntity);
            log.debug("add " + url);
        }
        var urlEntity = jpaUrlRepository.findByUrl(url);
        var tgChatEntity = jpaTgChatRepository.findById(tgChatId).orElseThrow();

        TgChatUrlEntity tgChatUrl = new TgChatUrlEntity();
        tgChatUrl.setTgChat(tgChatEntity);
        tgChatUrl.setUrl(urlEntity);

        jpaTgChatUrlRepository.saveAndFlush(tgChatUrl);

        LinkResponse linkResponse = new LinkResponse();
        return linkResponse.url(addLinkRequest.getLink()).id(tgChatId);
    }

    @Override
    public ListLinksResponse getAllLinks(Long tgChatId) {
        TgChatEntity tgChat = jpaTgChatRepository.findById(tgChatId).orElseThrow();
        List<LinkResponse> linkResponses =
            jpaTgChatUrlRepository.findAllByTgChat(tgChat).stream().map(tgChatUrlEntity -> {

                LinkResponse linkResponse = new LinkResponse();
                linkResponse.setUrl(URI.create(tgChatUrlEntity.getUrl().getUrl()));
                linkResponse.setId(tgChatUrlEntity.getTgChat().getId());

                return linkResponse;
            }).collect(Collectors.toList());
        return new ListLinksResponse().links(linkResponses);
    }

    @Override
    public LinkResponse deleteLink(Long tgChatId, RemoveLinkRequest removeLinkRequest) {

        TgChatUrlEntity tgChatUrl = new TgChatUrlEntity();

        tgChatUrl.setUrl(jpaUrlRepository.findByUrl(removeLinkRequest.getLink().toString()));
        tgChatUrl.setTgChat(jpaTgChatRepository.findById(tgChatId).orElseThrow());

        jpaTgChatUrlRepository.delete(tgChatUrl);

        return new LinkResponse().url(removeLinkRequest.getLink()).id(tgChatId);
    }

}
