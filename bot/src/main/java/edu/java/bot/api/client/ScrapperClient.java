package edu.java.bot.api.client;

import edu.java.bot.api.dto.AddLinkRequest;
import edu.java.bot.api.dto.LinkResponse;
import edu.java.bot.api.dto.ListLinksResponse;
import edu.java.bot.api.dto.RemoveLinkRequest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ScrapperClient {
    private static final String LINKS = "/links";
    private static final String LINK_TG_CHAT = "/tg-chat/{id}";
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
        return this.webClient.get().uri(LINKS).header(id.toString()).retrieve()
            .bodyToMono(ListLinksResponse.class);
    }

    public Mono<LinkResponse> postLinks(AddLinkRequest addLinkRequest, Long id) {
        return this.webClient.post().uri(LINKS).header(id.toString()).bodyValue(addLinkRequest).retrieve()
            .bodyToMono(LinkResponse.class);
    }

    public Mono<LinkResponse> deleteLinks(RemoveLinkRequest removeLinkRequest, Long id) {
        return this.webClient.post().uri(LINKS).header(id.toString()).bodyValue(removeLinkRequest).retrieve()
            .bodyToMono(LinkResponse.class);
    }
}
