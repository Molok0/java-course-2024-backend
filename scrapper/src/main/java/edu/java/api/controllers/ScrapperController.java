package edu.java.api.controllers;

import edu.java.api.services.ScrapperService;
import edu.java.scrapper.AddLinkRequest;
import edu.java.scrapper.LinkResponse;
import edu.java.scrapper.LinksApi;
import edu.java.scrapper.ListLinksResponse;
import edu.java.scrapper.RemoveLinkRequest;
import edu.java.scrapper.TgChatApi;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

@RestController
@RequiredArgsConstructor
public class ScrapperController implements TgChatApi, LinksApi {
    private ScrapperService scrapperService;

    @Autowired
    public ScrapperController(ScrapperService scrapperService) {
        this.scrapperService = scrapperService;
    }

    @Override
    public ResponseEntity<LinkResponse> linksDelete(Long tgChatId, RemoveLinkRequest removeLinkRequest) {
        return scrapperService.deleteLink(tgChatId, removeLinkRequest);
    }

    @Override
    public ResponseEntity<ListLinksResponse> linksGet(Long tgChatId) {
        return scrapperService.getAllLinks(tgChatId);
    }

    @Override
    public ResponseEntity<LinkResponse> linksPost(Long tgChatId, AddLinkRequest addLinkRequest) {
        return scrapperService.addLinks(tgChatId, addLinkRequest);
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return TgChatApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> tgChatIdDelete(Long id) {
        return scrapperService.deleteTgChat(id);
    }

    @Override
    public ResponseEntity<Void> tgChatIdPost(Long id) {
        return scrapperService.regNewTgChat(id);
    }
}
