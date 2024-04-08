package edu.java.shedule;

import edu.java.api.clients.BotClient;
import edu.java.api.services.JdbcUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LinkUpdaterScheduler {
    private final JdbcUpdateService jdbcUpdateService;

    @Autowired
    public LinkUpdaterScheduler(JdbcUpdateService jdbcUpdateService, BotClient botClient) {
        this.jdbcUpdateService = jdbcUpdateService;
    }

    @Scheduled(fixedDelayString = "#{@scheduler.interval().toMillis()}")
    public void update() {
        log.debug("calling update from LinkUpdaterScheduler");
        jdbcUpdateService.handleUpdate();
    }
}
