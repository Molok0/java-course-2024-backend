package edu.java.bot.controller;

import com.pengrad.telegrambot.TelegramBot;
import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.repositories.UserRepository;
import edu.java.bot.services.UpdatesListenerService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;

@Controller
public class BotController {

    private final ApplicationConfig applicationConfig;
    private UserRepository userRepository;
    private final UpdatesListenerService updatesListenerService;
    private final TelegramBot telegramBot;

    public BotController(
        ApplicationConfig applicationConfig,
        UserRepository userRepository,
        TelegramBot telegramBot,
        UpdatesListenerService updatesListenerService
    ) {
        this.applicationConfig = applicationConfig;
        this.userRepository = userRepository;
        this.updatesListenerService = updatesListenerService;
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    void run() {
        telegramBot.setUpdatesListener(updatesListenerService);
    }

}
