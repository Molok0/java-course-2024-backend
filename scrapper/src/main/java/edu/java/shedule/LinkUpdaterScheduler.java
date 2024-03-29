package edu.java.shedule;

import edu.java.api.dto.LinkUpdate;
import edu.java.api.services.UpdateService;
import edu.java.configuration.ClientConfig;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LinkUpdaterScheduler {
    private static final Logger LOGGER = Logger.getLogger(LinkUpdaterScheduler.class.getName());
    private final UpdateService updateService;
    private final ClientConfig clientConfig;

    @Autowired
    public LinkUpdaterScheduler(UpdateService updateService, ClientConfig clientConfig) {
        this.updateService = updateService;
        this.clientConfig = clientConfig;
    }

    @Scheduled(fixedDelayString = "#{@scheduler.interval().toMillis()}")
    public void update() {
        LOGGER.info("calling update from LinkUpdaterScheduler");
        List<LinkUpdate> linkUpdateList = updateService.updatesUrl();
        linkUpdateList.stream().map(linkUpdate -> {
            String url = linkUpdate.getUrl().toString();
            return null;
        });
    }
}
