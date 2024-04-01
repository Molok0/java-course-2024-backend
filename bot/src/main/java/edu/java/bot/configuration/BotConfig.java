package edu.java.bot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import edu.java.bot.processors.url.GitHubProcessor;
import edu.java.bot.processors.url.StackOverflowProcessor;
import edu.java.bot.processors.url.UrlProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class BotConfig {
    @Bean
    public TelegramBot telegramBot(ApplicationConfig applicationConfig) {
        return new TelegramBot(applicationConfig.telegramToken());
    }

    @Bean
    public UrlProcessor urlProcessor() {
        UrlProcessor urlProcessor = new GitHubProcessor(new StackOverflowProcessor(null));
        return urlProcessor;
    }
}
