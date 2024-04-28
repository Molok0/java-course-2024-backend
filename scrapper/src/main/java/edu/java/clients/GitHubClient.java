package edu.java.clients;

import edu.java.domain.dto.GitHubDto.UserRepositoryResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class GitHubClient {
    private final WebClient webClient;

    public GitHubClient(WebClient.Builder webClientBuilder, String defaultUrl) {
        this.webClient = webClientBuilder.baseUrl(defaultUrl).build();
    }

    public Mono<UserRepositoryResponse> getInfo(String name, String reposName) {
        return this.webClient.get().uri("/repos/{name}/{repos_name}", name, reposName).retrieve()
            .bodyToMono(UserRepositoryResponse.class);
    }

}
