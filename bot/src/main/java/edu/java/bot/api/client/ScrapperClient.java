package edu.java.bot.api.client;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ScrapperClient {
    private final WebClient webClient;
    public ScrapperClient(WebClient.Builder webClientBuilder, String defaultUrl) {
        this.webClient = webClientBuilder.baseUrl(defaultUrl).build();
    }
    public Mono<Void> regChat(Long id) {
        return this.webClient.post().uri("/tg-chat/{id}", id).retrieve()
            .bodyToMono(Void.class);
    }
    public Mono<Void> getLinks() {
        return this.webClient.get().uri("/links").retrieve()
            .bodyToMono(Void.class);
    }
    public Mono<Void> postLinks() {
        return this.webClient.post().uri("/links").retrieve()
            .bodyToMono(Void.class);
    }
}
