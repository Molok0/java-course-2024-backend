package edu.java.api.clients;

import edu.java.api.dto.LinkUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class BotClient {
    private final WebClient webClient;

    public BotClient(WebClient.Builder webClientBuilder, String defaultUrl) {
        this.webClient = webClientBuilder.baseUrl(defaultUrl).build();
    }

    public Mono<Void> update(LinkUpdate linkUpdate) {
        log.info("Отправка");
        return this.webClient.post().uri("/updates").bodyValue(linkUpdate).retrieve()
            .bodyToMono(Void.class);
    }
}
