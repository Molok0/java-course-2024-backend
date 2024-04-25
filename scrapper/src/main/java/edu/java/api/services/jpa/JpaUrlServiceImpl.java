package edu.java.api.services.jpa;

import edu.java.api.model.TgChatUrl;
import edu.java.api.model.repository.jpa.JpaTgChatRepository;
import edu.java.api.model.repository.jpa.JpaUrlRepository;
import edu.java.api.services.interfaces.UrlService;
import edu.java.generation.AddLinkRequest;
import edu.java.generation.LinkResponse;
import edu.java.generation.ListLinksResponse;
import edu.java.generation.RemoveLinkRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.Objects;

@Service
@Slf4j
public class JpaUrlServiceImpl implements UrlService {

    private final JpaUrlRepository jpaUrlRepository;
    private final JpaTgChatRepository jpaTgChatRepository;
    @Autowired
    public JpaUrlServiceImpl(JpaUrlRepository jpaUrlRepository, JpaTgChatRepository jpaTgChatRepository) {
        this.jpaUrlRepository = jpaUrlRepository;
        this.jpaTgChatRepository = jpaTgChatRepository;
    }

    @Override
    public LinkResponse addLinks(Long tgChatId, AddLinkRequest addLinkRequest) {
        String url = addLinkRequest.getLink().toString();

//        if (Objects.isNull(jpaUrlRepository.findIdByUrl(url))) {
//
//            jpaUrlRepository.saveAndFlush(addLinkRequest.getLink().toString(), LocalTime.now());
//            log.debug("add " + url);
//        }
        Long urlId = jpaUrlRepository.findIdByUrl(url);

//        TgChatUrl tgChatUrl = new TgChatUrl();
//        tgChatUrl.setTgChatId(tgChatId);
//        tgChatUrl.setUrlId(urlId);
//
//        tgChatUrlRepository.add(tgChatUrl);
//        LinkResponse linkResponse = new LinkResponse();
//        return linkResponse.url(addLinkRequest.getLink()).id(tgChatId);
        return null;
    }

    @Override
    public ListLinksResponse getAllLinks(Long tgChatId) {
        return null;
    }

    @Override
    public LinkResponse deleteLink(Long tgChatId, RemoveLinkRequest removeLinkRequest) {
        return null;
    }

}
