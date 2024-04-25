package edu.java.configuration;

import edu.java.api.clients.BotClient;
import edu.java.api.model.repository.jdbc.JdbcTgChatUrlRepositoryImpl;
import edu.java.api.model.repository.jdbc.JdbcUrlRepositoryImpl;
import edu.java.api.services.JdbcLinkService;
import edu.java.api.services.LinkService;
import edu.java.processors.UrlProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
public class JdbcAccessConfiguration {
    @Bean
    public LinkService linkService(
        JdbcTgChatUrlRepositoryImpl jdbcTgChatUrlRepository,
        JdbcUrlRepositoryImpl jdbcUrlRepository,
        UrlProcessor urlProcessor,
        BotClient botClient
    ) {
        return new JdbcLinkService(jdbcTgChatUrlRepository, jdbcUrlRepository, urlProcessor, botClient);
    }
}
