package edu.java.api.services;

import edu.java.api.clients.BotClient;
import edu.java.api.dto.LinkUpdate;
import edu.java.api.model.Url;
import edu.java.api.model.repository.interfaces.TgChatUrlRepository;
import edu.java.api.model.repository.interfaces.UrlRepository;
import edu.java.processors.UrlProcessor;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JdbcLinkService implements LinkService {
    public final BotClient botClient;
    private final TgChatUrlRepository jdbcTgChatUrlRepository;
    private final UrlRepository jdbcUrlRepository;
    private final UrlProcessor urlProcessor;
    public final static int MINUS_HOURS = 1;

    public JdbcLinkService(
        TgChatUrlRepository jdbcTgChatUrlRepository,
        UrlRepository jdbcUrlRepository,
        UrlProcessor urlProcessor, BotClient botClient
    ) {
        this.jdbcTgChatUrlRepository = jdbcTgChatUrlRepository;
        this.jdbcUrlRepository = jdbcUrlRepository;
        this.urlProcessor = urlProcessor;
        this.botClient = botClient;
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

    public void handleUpdate() {
        List<LinkUpdate> linkUpdateList = getOldUrl();

        if (!Objects.isNull(linkUpdateList)) {
            log.debug("Адреса на проверку:\n" + linkUpdateList);

            linkUpdateList.forEach(linkUpdate -> {
                String url = linkUpdate.getUrl().toString();
                String date = urlProcessor.handle(url);

                if (updateUrl(linkUpdate.getId(), date)) {
                    // Отправить боту уведомление
                    log.info("Отправить боту уведомление");
                    linkUpdate.setDescription(date);
                    botClient.update(linkUpdate).block();
                }
            });
        }
    }

    public boolean updateUrl(Long id, String time) {
        // Проверяем есть ли изменения и заодно обновляем
        int countRow = jdbcUrlRepository.updateByTimeUrl(id, time);
        return countRow != 0;
    }
}
