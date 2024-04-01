package edu.java.bot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import edu.java.bot.api.client.ScrapperClient;
import edu.java.bot.processors.url.GitHubProcessor;
import edu.java.bot.processors.url.StackOverflowProcessor;
import edu.java.bot.processors.url.UrlProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
public class BotConfig {
    private WebClient.Builder webClientBuilder;

    @Autowired
    public BotConfig(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Bean
    public TelegramBot telegramBot(ApplicationConfig applicationConfig) {
        return new TelegramBot(applicationConfig.telegramToken());
    }

    @Bean
    public ScrapperClient scrapperClient(ApplicationConfig applicationConfig) {
        return new ScrapperClient(webClientBuilder, applicationConfig.urlClient().scrapperDefaultUrl());
    }

    @Bean
    public UrlProcessor urlProcessor() {
        return new GitHubProcessor(new StackOverflowProcessor(null));
    }
}
