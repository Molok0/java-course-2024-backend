package edu.java.service.services;

import edu.java.api.dto.LinkUpdate;
import edu.java.domain.Url;
import edu.java.domain.repository.interfaces.TgChatUrlRepository;
import edu.java.domain.repository.interfaces.UrlRepository;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JdbcLinkService implements LinkService {
    private final TgChatUrlRepository jdbcTgChatUrlRepository;
    private final UrlRepository jdbcUrlRepository;
    public final static int MINUS_HOURS = 1;

    public JdbcLinkService(
        TgChatUrlRepository jdbcTgChatUrlRepository,
        UrlRepository jdbcUrlRepository
    ) {
        this.jdbcTgChatUrlRepository = jdbcTgChatUrlRepository;
        this.jdbcUrlRepository = jdbcUrlRepository;
    }

    public List<LinkUpdate> getOldUrl() {
        OffsetDateTime offsetDateTimeMinusHours = OffsetDateTime.now().minusHours(MINUS_HOURS);
        List<Url> urls = jdbcUrlRepository.findByLastCheckTime(offsetDateTimeMinusHours);
        if (Objects.isNull(urls)) {
            return null;
        }
        return urls.stream().map(url -> {
            LinkUpdate linkUpdate = new LinkUpdate();
            linkUpdate.setUrl(URI.create(url.getUrl()));
            linkUpdate.setId(url.getId());
            linkUpdate.setTgChatIds(jdbcTgChatUrlRepository.findByUrlId(url.getId()));
            return linkUpdate;
        }).collect(Collectors.toList());
    }

    public boolean updateUrl(Long id, OffsetDateTime time) {
        // Проверяем есть ли изменения и заодно обновляем
        int countRow = jdbcUrlRepository.updateByTimeUrl(id, time);
        return countRow != 0;
    }
}
