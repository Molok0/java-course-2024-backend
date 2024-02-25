package edu.java.clients;

import edu.java.dto.GitHubDto.UserRepositoryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GitHubClient {
    @Value("${github_default_url}")
    private static String defaultUrl;

    private final WebClient webClient;

    public GitHubClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(defaultUrl).build();
    }

    public Mono<UserRepositoryResponse> getInfo(String name, String reposName) {
        return this.webClient.get().uri("/repos/{name}/{repos_name}", name, reposName).retrieve()
            .bodyToMono(UserRepositoryResponse.class);
    }

}
