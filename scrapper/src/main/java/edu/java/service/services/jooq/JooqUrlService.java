package edu.java.service.services.jooq;

import edu.java.domain.generation.AddLinkRequest;
import edu.java.domain.generation.LinkResponse;
import edu.java.domain.generation.ListLinksResponse;
import edu.java.domain.generation.RemoveLinkRequest;
import edu.java.service.services.interfaces.UrlService;

public class JooqUrlService implements UrlService {
    @Override
    public LinkResponse addLinks(Long tgChatId, AddLinkRequest addLinkRequest) {
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
