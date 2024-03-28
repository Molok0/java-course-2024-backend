package edu.java.api.services.interfaces;

import edu.java.generation.AddLinkRequest;
import edu.java.generation.LinkResponse;
import edu.java.generation.ListLinksResponse;
import edu.java.generation.RemoveLinkRequest;

public interface UrlService {
    LinkResponse addLinks(Long tgChatId, AddLinkRequest addLinkRequest);

    ListLinksResponse getAllLinks(Long tgChatId);

    LinkResponse deleteLink(Long tgChatId, RemoveLinkRequest removeLinkRequest);
}
