package edu.java.clients;

import edu.java.domain.dto.StackOverflowDto.ItemsDto;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class StackOverflowClient {
    private final WebClient webClient;

    public StackOverflowClient(WebClient.Builder webClientBuilder, String defaultUrl) {
        this.webClient = webClientBuilder.baseUrl(defaultUrl).build();
    }

    public Mono<ItemsDto> getQuestions(String id) {
        return this.webClient.get().uri("/questions/{id}?order=desc&sort=activity&site=stackoverflow", id).retrieve()
            .bodyToMono(ItemsDto.class);
    }

}
