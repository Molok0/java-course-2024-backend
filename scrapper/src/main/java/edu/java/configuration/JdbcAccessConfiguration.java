package edu.java.configuration;

import edu.java.api.clients.BotClient;
import edu.java.api.model.repository.interfaces.TgChatRepository;
import edu.java.api.model.repository.interfaces.TgChatUrlRepository;
import edu.java.api.model.repository.interfaces.UrlRepository;
import edu.java.api.model.repository.jdbc.JdbcTgChatRepositoryImpl;
import edu.java.api.model.repository.jdbc.JdbcTgChatUrlRepositoryImpl;
import edu.java.api.model.repository.jdbc.JdbcUrlRepositoryImpl;
import edu.java.api.services.JdbcLinkService;
import edu.java.api.services.LinkService;
import edu.java.api.services.interfaces.TgChatService;
import edu.java.api.services.interfaces.UrlService;
import edu.java.api.services.jdbc.JdbcTgChatServiceImpl;
import edu.java.api.services.jdbc.JdbcUrlServiceImpl;
import edu.java.processors.UrlProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
public class JdbcAccessConfiguration {
    @Bean
    UrlService jdbcUrlServiceImpl(TgChatUrlRepository tgChatUrlRepository, UrlRepository jdbcUrlRepositoryImpl) {
        return new JdbcUrlServiceImpl(tgChatUrlRepository, jdbcUrlRepositoryImpl);
    }

    @Bean
    TgChatService jdbcTgChatServiceImpl(TgChatRepository jdbcTgChatRepository) {
        return new JdbcTgChatServiceImpl(jdbcTgChatRepository);
    }

    @Bean
    TgChatRepository tgChatRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcTgChatRepositoryImpl(jdbcTemplate);
    }

    @Bean
    UrlRepository urlRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcUrlRepositoryImpl(jdbcTemplate);
    }

    @Bean
    TgChatUrlRepository tgChatUrlRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcTgChatUrlRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public LinkService linkService(
        TgChatUrlRepository tgChatUrlRepository,
        UrlRepository urlRepository,
        UrlProcessor urlProcessor,
        BotClient botClient
    ) {

        return new JdbcLinkService(tgChatUrlRepository, urlRepository, urlProcessor, botClient);
    }
}
