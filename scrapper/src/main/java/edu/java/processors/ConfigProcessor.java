package edu.java.processors;

import edu.java.configuration.ClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigProcessor {
    @Bean
    public UrlProcessor urlProcessor(ClientConfig clientConfig) {
        UrlProcessor urlProcessor = new GitHubProcessor(clientConfig);
        urlProcessor.setNext(new StackOverflowProcessor(clientConfig));
        urlProcessor.getNext().setNext(null);
        return urlProcessor;
    }
}
