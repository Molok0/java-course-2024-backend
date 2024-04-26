package edu.java.bot.api.client;

import edu.java.bot.api.dto.AddLinkRequest;
import edu.java.bot.api.dto.LinkResponse;
import edu.java.bot.api.dto.ListLinksResponse;
import edu.java.bot.api.dto.RemoveLinkRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ScrapperClient {
    private static final String LINKS = "/links";
    private static final String LINK_TG_CHAT = "/tg-chat/{id}";
    private static final String HEADER_NAME = "Tg-Chat-Id";
    private final WebClient webClient;

    public ScrapperClient(WebClient.Builder webClientBuilder, String defaultUrl) {
        this.webClient = webClientBuilder.baseUrl(defaultUrl).build();
    }

    public Mono<Void> regChat(Long id) {
        return this.webClient.post().uri(LINK_TG_CHAT, id).retrieve()
            .bodyToMono(Void.class);
    }

    public Mono<Void> deleteChat(Long id) {
        return this.webClient.post().uri(LINK_TG_CHAT, id).retrieve()
            .bodyToMono(Void.class);
    }

    public Mono<ListLinksResponse> getLinks(Long id) {
        return this.webClient.get()
            .uri(LINKS)
            .header(HEADER_NAME, id.toString())
            .retrieve()
            .bodyToMono(ListLinksResponse.class);
    }

    public Mono<ResponseEntity<LinkResponse>> postLinks(AddLinkRequest addLinkRequest, Long id) {
        return this.webClient.post()
            .uri(LINKS)
            .header(HEADER_NAME, id.toString())
            .bodyValue(addLinkRequest)
            .retrieve()
            .toEntity(
                LinkResponse.class);
    }

    public Mono<LinkResponse> deleteLinks(RemoveLinkRequest removeLinkRequest, Long id) {
        return this.webClient.method(HttpMethod.DELETE)
            .uri(LINKS)
            .header(HEADER_NAME, String.valueOf(id))
            .bodyValue(removeLinkRequest)
            .retrieve()
            .bodyToMono(LinkResponse.class);
    }
}
