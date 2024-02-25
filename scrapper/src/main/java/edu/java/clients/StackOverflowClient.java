package edu.java.clients;

import edu.java.dto.StackOverflowDto.ItemsDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class StackOverflowClient {
    // Лучше записать в yaml
    private static final String DEFAULT_URL = "https://api.stackexchange.com/2.3";

    private final WebClient webClient;

    public StackOverflowClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(DEFAULT_URL).build();
    }

    public Mono<ItemsDto> getQuestions(String id) {
        return this.webClient.get().uri("/questions/{id}?order=desc&sort=activity&site=stackoverflow", id).retrieve()
            .bodyToMono(ItemsDto.class);
    }

}
