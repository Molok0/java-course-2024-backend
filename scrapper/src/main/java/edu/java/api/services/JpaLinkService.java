package edu.java.api.services;

import edu.java.api.clients.BotClient;
import edu.java.api.dto.LinkUpdate;
import edu.java.api.model.entity.UrlEntity;
import edu.java.api.model.repository.jpa.JpaTgChatUrlRepository;
import edu.java.api.model.repository.jpa.JpaUrlRepository;
import edu.java.processors.UrlProcessor;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JpaLinkService implements LinkService {
    public final BotClient botClient;
    private final JpaTgChatUrlRepository jpaTgChatUrlRepository;
    private final JpaUrlRepository jpaUrlRepository;
    private final UrlProcessor urlProcessor;
    public final static int MINUS_HOURS = 1;

    public JpaLinkService(
        BotClient botClient,
        JpaTgChatUrlRepository jdbcTgChatUrlRepository,
        JpaUrlRepository jdbcUrlRepository,
        UrlProcessor urlProcessor
    ) {
        this.botClient = botClient;
        this.jpaTgChatUrlRepository = jdbcTgChatUrlRepository;
        this.jpaUrlRepository = jdbcUrlRepository;

        this.urlProcessor = urlProcessor;
    }

    @Override
    public void handleUpdate() {

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
    public boolean updateUrl(Long id, String time) {
        // Проверяем есть ли изменения и заодно обновляем
//        int countRow = jpaUrlRepository.updateByTimeUrl(id, time);
//        return countRow != 0;
        return false;
    }
}
