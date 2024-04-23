package edu.java.api.services;

import edu.java.api.clients.BotClient;
import edu.java.api.dto.LinkUpdate;
import edu.java.api.model.Url;
import edu.java.api.model.repository.jdbc.JdbcTgChatUrlRepositoryImpl;
import edu.java.api.model.repository.jdbc.JdbcUrlRepositoryImpl;
import edu.java.processors.UrlProcessor;
import java.net.URI;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JdbcUpdateService {

    private final JdbcTgChatUrlRepositoryImpl jdbcTgChatUrlRepository;
    private final JdbcUrlRepositoryImpl jdbcUrlRepository;
    private final UrlProcessor urlProcessor;
    private final BotClient botClient;
    private static final int MINUS_HOURS = 1;

    @Autowired
    public JdbcUpdateService(
        JdbcTgChatUrlRepositoryImpl jdbcTgChatUrlRepository,
        JdbcUrlRepositoryImpl jdbcUrlRepository,
        UrlProcessor urlProcessor, BotClient botClient
    ) {
        this.jdbcTgChatUrlRepository = jdbcTgChatUrlRepository;
        this.jdbcUrlRepository = jdbcUrlRepository;
        this.urlProcessor = urlProcessor;
        this.botClient = botClient;
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

    protected List<LinkUpdate> getOldUrl() {
        LocalTime localTimeMinusHours = LocalTime.now().minusHours(MINUS_HOURS);
        List<Url> urls = jdbcUrlRepository.findByLastCheckTime(localTimeMinusHours);
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

    protected boolean updateUrl(Long id, String time) {
        // Проверяем есть ли изменения и заодно обновляем
        int countRow = jdbcUrlRepository.updateByTimeUrl(id, time);
        return countRow != 0;
    }
}
