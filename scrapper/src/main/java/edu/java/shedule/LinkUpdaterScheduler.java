package edu.java.shedule;

import edu.java.api.clients.BotClient;
import edu.java.api.dto.LinkUpdate;
import edu.java.api.services.LinkService;
import edu.java.processors.UrlProcessor;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LinkUpdaterScheduler {
    private final LinkService linkService;
    private final UrlProcessor urlProcessor;
    private final BotClient botClient;

    @Autowired
    public LinkUpdaterScheduler(LinkService linkService, UrlProcessor urlProcessor, BotClient botClient) {
        this.linkService = linkService;
        this.urlProcessor = urlProcessor;
        this.botClient = botClient;
    }

    @Scheduled(fixedDelayString = "#{@scheduler.interval().toMillis()}")
    public void update() {

        log.debug("calling update from LinkUpdaterScheduler");

        List<LinkUpdate> linkUpdateList = linkService.getOldUrl();

        if (!Objects.isNull(linkUpdateList)) {
            log.debug("Адреса на проверку:\n" + linkUpdateList);

            linkUpdateList.forEach(linkUpdate -> {
                String url = linkUpdate.getUrl().toString();
                OffsetDateTime date = OffsetDateTime.parse(urlProcessor.handle(url));

                if (linkService.updateUrl(linkUpdate.getId(), date)) {
                    // Отправить боту уведомление
                    log.info("Отправить боту уведомление");
                    linkUpdate.setDescription(String.valueOf(date));
                    botClient.update(linkUpdate).block();
                }
            });
        }
    }
}
