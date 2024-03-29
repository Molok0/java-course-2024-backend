package edu.java.api.services;

import edu.java.api.dto.LinkUpdate;
import edu.java.api.model.repository.jdbc.JdbcTgChatUrlRepositoryImpl;
import edu.java.api.model.repository.jdbc.JdbcUrlRepositoryImpl;
import java.net.URI;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateService {

    private final JdbcTgChatUrlRepositoryImpl jdbcTgChatUrlRepository;
    private final JdbcUrlRepositoryImpl jdbcUrlRepository;

    @Autowired
    public UpdateService(
        JdbcTgChatUrlRepositoryImpl jdbcTgChatUrlRepository,
        JdbcUrlRepositoryImpl jdbcUrlRepository
    ) {
        this.jdbcTgChatUrlRepository = jdbcTgChatUrlRepository;
        this.jdbcUrlRepository = jdbcUrlRepository;
    }

    public List<LinkUpdate> updatesUrl() {
        LocalTime localTimeMinusHours = LocalTime.now();

        List<LinkUpdate> linkUpdates =
            jdbcUrlRepository.findByLastCheckTime(localTimeMinusHours).stream().map(url -> {
                LinkUpdate linkUpdate = new LinkUpdate();

                linkUpdate.setUrl(URI.create(url.getUrl()));
                linkUpdate.setId(url.getId());

                linkUpdate.setTgChatIds(jdbcTgChatUrlRepository.findByUrlId(url.getId()));
                return linkUpdate;
            }).collect(Collectors.toList());

        return linkUpdates;
    }
}
