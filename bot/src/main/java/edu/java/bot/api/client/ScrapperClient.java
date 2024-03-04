package edu.java.bot.api.client;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.net.URI;

public class ScrapperClient {
    private static final String LINKS = "/links";
    private final WebClient webClient;

    public ScrapperClient(WebClient.Builder webClientBuilder, String defaultUrl) {
        this.webClient = webClientBuilder.baseUrl(defaultUrl).build();
    }

    public Mono<Void> regChat(Long id) {
        return this.webClient.post().uri("/tg-chat/{id}", id).retrieve()
            .bodyToMono(Void.class);
    }
    public Mono<Void> deleteChat(Long id) {
        return this.webClient.post().uri("/tg-chat/{id}", id).retrieve()
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
}
