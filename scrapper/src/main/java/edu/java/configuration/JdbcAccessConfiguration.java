package edu.java.configuration;

import edu.java.domain.repository.interfaces.TgChatRepository;
import edu.java.domain.repository.interfaces.TgChatUrlRepository;
import edu.java.domain.repository.interfaces.UrlRepository;
import edu.java.domain.repository.jdbc.JdbcTgChatRepositoryImpl;
import edu.java.domain.repository.jdbc.JdbcTgChatUrlRepositoryImpl;
import edu.java.domain.repository.jdbc.JdbcUrlRepositoryImpl;
import edu.java.service.services.JdbcLinkService;
import edu.java.service.services.LinkService;
import edu.java.service.services.interfaces.TgChatService;
import edu.java.service.services.interfaces.UrlService;
import edu.java.service.services.jdbc.JdbcTgChatServiceImpl;
import edu.java.service.services.jdbc.JdbcUrlServiceImpl;
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
        UrlRepository urlRepository
    ) {
        return new JdbcLinkService(tgChatUrlRepository, urlRepository);
    }
}
