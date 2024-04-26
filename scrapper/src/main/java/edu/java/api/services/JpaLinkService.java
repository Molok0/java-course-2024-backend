package edu.java.api.services;

import edu.java.api.dto.LinkUpdate;
import edu.java.api.model.entity.UrlEntity;
import edu.java.api.model.repository.jpa.JpaTgChatUrlRepository;
import edu.java.api.model.repository.jpa.JpaUrlRepository;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JpaLinkService implements LinkService {
    private final JpaTgChatUrlRepository jpaTgChatUrlRepository;
    private final JpaUrlRepository jpaUrlRepository;
    public final static int MINUS_HOURS = 1;

    public JpaLinkService(
        JpaTgChatUrlRepository jdbcTgChatUrlRepository,
        JpaUrlRepository jdbcUrlRepository
    ) {
        this.jpaTgChatUrlRepository = jdbcTgChatUrlRepository;
        this.jpaUrlRepository = jdbcUrlRepository;
    }

    @Override
    public List<LinkUpdate> getOldUrl() {
        OffsetDateTime offsetDateTimeMinusHours = OffsetDateTime.now().minusHours(MINUS_HOURS);
        List<UrlEntity> urls = jpaUrlRepository.findByLastCheckBefore(offsetDateTimeMinusHours);

        if (Objects.isNull(urls)) {
            return null;
        }

        return urls.stream().map(url -> {
            UrlEntity urlEntity = jpaUrlRepository.findByUrl(url.getUrl());

            LinkUpdate linkUpdate = new LinkUpdate();
            linkUpdate.setUrl(URI.create(url.getUrl()));
            linkUpdate.setId(url.getId());

            linkUpdate.setTgChatIds(jpaTgChatUrlRepository.findAllByUrl(urlEntity).stream().map(tgChatUrlEntity -> {
                return tgChatUrlEntity.getTgChat().getId();
            }).collect(Collectors.toList()));

            return linkUpdate;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean updateUrl(Long id, OffsetDateTime time) {

        // Проверяем есть ли изменения и заодно обновляем
        List<UrlEntity> urlEntities = jpaUrlRepository.findByLastCheckBefore(time);

        long countRow = 0L;

        for (UrlEntity entity : urlEntities) {
            entity.setLastCheck(OffsetDateTime.now());
            entity.setLastChange(time);
            countRow += 1;
        }

        jpaUrlRepository.saveAll(urlEntities);

        log.info("Проверено ссылок " + urlEntities.size());
        return countRow != 0;

    }
}
