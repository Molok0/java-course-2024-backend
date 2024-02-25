package edu.java.clients;

import edu.java.dto.StackOverflowDto.ItemsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class StackOverflowClient {

    @Value("${stackoverflow_default_url}")
    private static String DEFAULT_URL;

    private final WebClient webClient;

    public StackOverflowClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(DEFAULT_URL).build();
    }

    public Mono<ItemsDto> getQuestions(String id) {
        return this.webClient.get().uri("/questions/{id}?order=desc&sort=activity&site=stackoverflow", id).retrieve()
            .bodyToMono(ItemsDto.class);
    }

}
