package edu.java.shedule;

import edu.java.api.clients.BotClient;
import edu.java.api.services.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LinkUpdaterScheduler {
    private final LinkService linkService;

    @Autowired
    public LinkUpdaterScheduler(LinkService linkService, BotClient botClient) {
        this.linkService = linkService;
    }

    @Scheduled(fixedDelayString = "#{@scheduler.interval().toMillis()}")
    public void update() {
        log.debug("calling update from LinkUpdaterScheduler");
        linkService.handleUpdate();
    }
}
