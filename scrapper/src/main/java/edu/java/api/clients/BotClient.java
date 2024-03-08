package edu.java.api.clients;

import edu.java.api.dto.LinkUpdate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class BotClient {
    private final WebClient webClient;

    public BotClient(WebClient.Builder webClientBuilder, String defaultUrl) {
        this.webClient = webClientBuilder.baseUrl(defaultUrl).build();
    }

    public Mono<Void> update(LinkUpdate linkUpdate) {
        return this.webClient.post().uri("/update").bodyValue(linkUpdate).retrieve()
            .bodyToMono(Void.class);
    }
}
