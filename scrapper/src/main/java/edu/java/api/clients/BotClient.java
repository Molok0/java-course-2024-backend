package edu.java.api.clients;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class BotClient {
    private final WebClient webClient;

    public BotClient(WebClient.Builder webClientBuilder, String defaultUrl) {
        this.webClient = webClientBuilder.baseUrl(defaultUrl).build();
    }

    public Mono<Void> update() {
        return this.webClient.post().uri("/update").retrieve()
            .bodyToMono(Void.class);
    }
}
