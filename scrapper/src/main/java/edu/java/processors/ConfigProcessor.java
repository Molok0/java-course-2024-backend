package edu.java.processors;

import edu.java.clients.GitHubClient;
import edu.java.clients.StackOverflowClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigProcessor {
    @Bean
    public UrlProcessor urlProcessor(StackOverflowClient stackOverflowClient, GitHubClient gitHubClient) {
        return new GitHubProcessor(new StackOverflowProcessor(null, stackOverflowClient), gitHubClient);
    }
}
