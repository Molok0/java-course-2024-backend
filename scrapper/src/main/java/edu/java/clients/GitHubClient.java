package edu.java.clients;

import edu.java.dto.GitHubDto.UserRepositoryResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GitHubClient {
    // Лучше записать в yaml
    private static final String DEFAULT_URL = "https://api.github.com/";

    private final WebClient webClient;

    public GitHubClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(DEFAULT_URL).build();
    }

    public Mono<UserRepositoryResponse> getInfo(String name, String reposName) {
        return this.webClient.get().uri("/repos/{name}/{repos_name}", name, reposName).retrieve()
            .bodyToMono(UserRepositoryResponse.class);
    }

}
