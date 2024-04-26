package edu.java.configuration;

import edu.java.api.model.repository.jpa.JpaTgChatRepository;
import edu.java.api.model.repository.jpa.JpaTgChatUrlRepository;
import edu.java.api.model.repository.jpa.JpaUrlRepository;
import edu.java.api.services.JpaLinkService;
import edu.java.api.services.LinkService;
import edu.java.api.services.interfaces.TgChatService;
import edu.java.api.services.interfaces.UrlService;
import edu.java.api.services.jpa.JpaTgChatServiceImpl;
import edu.java.api.services.jpa.JpaUrlServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {
    @Bean
    UrlService jpaUrlServiceImpl(
        JpaTgChatUrlRepository jpaTgChatUrlRepository,
        JpaUrlRepository jpaUrlRepository,
        JpaTgChatRepository jpaTgChatRepository
    ) {
        return new JpaUrlServiceImpl(jpaUrlRepository, jpaTgChatUrlRepository, jpaTgChatRepository);
    }

    @Bean
    TgChatService jpaTgChatServiceImpl(JpaTgChatRepository jpaTgChatRepository) {
        return new JpaTgChatServiceImpl(jpaTgChatRepository);
    }

    @Bean
    public LinkService linkService(
        JpaTgChatUrlRepository tgChatUrlRepository,
        JpaUrlRepository urlRepository
    ) {

        return new JpaLinkService(tgChatUrlRepository, urlRepository);
    }
}
