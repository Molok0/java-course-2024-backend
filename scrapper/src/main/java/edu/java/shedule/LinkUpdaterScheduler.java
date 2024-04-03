package edu.java.shedule;

import edu.java.api.dto.LinkUpdate;
import edu.java.api.services.UpdateService;
import edu.java.configuration.ClientConfig;
import edu.java.processors.UrlProcessor;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LinkUpdaterScheduler {
    private final UpdateService updateService;
    private final ClientConfig clientConfig;
    private final UrlProcessor urlProcessor;

    @Autowired
    public LinkUpdaterScheduler(UpdateService updateService, ClientConfig clientConfig, UrlProcessor urlProcessor) {
        this.updateService = updateService;
        this.clientConfig = clientConfig;
        this.urlProcessor = urlProcessor;
    }

    @Scheduled(fixedDelayString = "#{@scheduler.interval().toMillis()}")
    public void update() {
        log.debug("calling update from LinkUpdaterScheduler");
        List<LinkUpdate> linkUpdateList = updateService.updatesUrl();
        log.debug(linkUpdateList.toString() + " адреса на проверку ");
        var res = linkUpdateList.stream().map(linkUpdate -> {
            String url = linkUpdate.getUrl().toString();
            String a = urlProcessor.handle(url);
            return a;
        }).toList();
        log.debug(String.valueOf(res));
    }
}
