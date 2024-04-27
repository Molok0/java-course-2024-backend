package edu.java.api.services.jooq;

import edu.java.api.model.repository.interfaces.UrlRepository;
import edu.java.api.services.interfaces.UrlService;
import edu.java.generation.AddLinkRequest;
import edu.java.generation.LinkResponse;
import edu.java.generation.ListLinksResponse;
import edu.java.generation.RemoveLinkRequest;

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
