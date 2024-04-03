package edu.java.api.services;

import edu.java.api.dto.LinkUpdate;
import edu.java.api.model.repository.jdbc.JdbcTgChatUrlRepositoryImpl;
import edu.java.api.model.repository.jdbc.JdbcUrlRepositoryImpl;
import java.net.URI;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UpdateService {

    private final JdbcTgChatUrlRepositoryImpl jdbcTgChatUrlRepository;
    private final JdbcUrlRepositoryImpl jdbcUrlRepository;
    private static final int MINUS_HOURS = 1;

    @Autowired
    public UpdateService(
        JdbcTgChatUrlRepositoryImpl jdbcTgChatUrlRepository,
        JdbcUrlRepositoryImpl jdbcUrlRepository
    ) {
        this.jdbcTgChatUrlRepository = jdbcTgChatUrlRepository;
        this.jdbcUrlRepository = jdbcUrlRepository;
    }

    public List<LinkUpdate> updatesUrl() {
        LocalTime localTimeMinusHours = LocalTime.now().plusHours(MINUS_HOURS);
        return jdbcUrlRepository.findByLastCheckTime(localTimeMinusHours).stream().map(url -> {
            LinkUpdate linkUpdate = new LinkUpdate();
            log.debug(url.getUrl());
            linkUpdate.setUrl(URI.create(url.getUrl()));
            linkUpdate.setId(url.getId());

            linkUpdate.setTgChatIds(jdbcTgChatUrlRepository.findByUrlId(url.getId()));
            return linkUpdate;
        }).collect(Collectors.toList());
    }
}
