package edu.java.bot.api.client;

import java.net.URI;
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

    public Mono<Void> getLinks(Long id) {
        return this.webClient.get().uri(LINKS).header(id.toString()).retrieve()
            .bodyToMono(Void.class);
    }

    public Mono<Void> postLinks(URI uri, Long id) {
        return this.webClient.post().uri(LINKS).header(id.toString()).bodyValue(uri).retrieve()
            .bodyToMono(Void.class);
    }

    public Mono<Void> deleteLinks(URI uri, Long id) {
        return this.webClient.post().uri(LINKS).header(id.toString()).bodyValue(uri).retrieve()
            .bodyToMono(Void.class);
    }
}
