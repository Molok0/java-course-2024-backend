package edu.java.service.services.interfaces;

import edu.java.domain.generation.AddLinkRequest;
import edu.java.domain.generation.LinkResponse;
import edu.java.domain.generation.ListLinksResponse;
import edu.java.domain.generation.RemoveLinkRequest;

public interface UrlService {
    LinkResponse addLinks(Long tgChatId, AddLinkRequest addLinkRequest);

    ListLinksResponse getAllLinks(Long tgChatId);

    LinkResponse deleteLink(Long tgChatId, RemoveLinkRequest removeLinkRequest);
}
