package edu.java.api.services;

import edu.java.api.clients.BotClient;
import edu.java.api.dto.LinkUpdate;
import edu.java.processors.UrlProcessor;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class UpdateService {
    protected final UrlProcessor urlProcessor;
    protected final BotClient botClient;
    protected static final int MINUS_HOURS = 1;

    public UpdateService(
        UrlProcessor urlProcessor, BotClient botClient
    ) {
        this.urlProcessor = urlProcessor;
        this.botClient = botClient;
    }

    public final void handleUpdate() {

        List<LinkUpdate> linkUpdateList = getOldUrl();

        log.debug("Адреса на проверку:\n" + linkUpdateList.toString());

        linkUpdateList.forEach(linkUpdate -> {
            String url = linkUpdate.getUrl().toString();
            String date = urlProcessor.handle(url);
            if (updateUrl(linkUpdate.getId(), date)) {
                // Отправить боту уведомление
                linkUpdate.setDescription(date);
                botClient.update(linkUpdate);
            }
        });
    }

    protected abstract List<LinkUpdate> getOldUrl();

    protected abstract boolean updateUrl(Long id, String time);
}
