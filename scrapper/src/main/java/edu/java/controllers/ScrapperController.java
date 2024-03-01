package edu.java.controllers;

import edu.java.scrapper.AddLinkRequest;
import edu.java.scrapper.LinkResponse;
import edu.java.scrapper.LinksApi;
import edu.java.scrapper.ListLinksResponse;
import edu.java.scrapper.RemoveLinkRequest;
import edu.java.scrapper.TgChatApi;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

@RestController
@RequiredArgsConstructor
public class ScrapperController implements TgChatApi, LinksApi {
    @Override
    public ResponseEntity<LinkResponse> linksDelete(Long tgChatId, RemoveLinkRequest removeLinkRequest) {
        return LinksApi.super.linksDelete(tgChatId, removeLinkRequest);
    }

    @Override
    public ResponseEntity<ListLinksResponse> linksGet(Long tgChatId) {
        return LinksApi.super.linksGet(tgChatId);
    }

    @Override
    public ResponseEntity<LinkResponse> linksPost(Long tgChatId, AddLinkRequest addLinkRequest) {
        return LinksApi.super.linksPost(tgChatId, addLinkRequest);
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return TgChatApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> tgChatIdDelete(Long id) {
        return TgChatApi.super.tgChatIdDelete(id);
    }

    @Override
    public ResponseEntity<Void> tgChatIdPost(Long id) {
        return TgChatApi.super.tgChatIdPost(id);
    }
}
