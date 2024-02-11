package edu.java.bot.controller;

import com.pengrad.telegrambot.TelegramBot;
import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.repositories.UserRepository;
import edu.java.bot.services.UpdatesListenerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BotController {

    private final ApplicationConfig applicationConfig;
    private UserRepository userRepository;

    @Autowired
    public BotController(ApplicationConfig applicationConfig, UserRepository userRepository) {
        this.applicationConfig = applicationConfig;
        this.userRepository = userRepository;
    }

    @PostConstruct
    void run() {
        TelegramBot telegramBot = new TelegramBot(applicationConfig.telegramToken());
        UpdatesListenerService botUpdatesListenerService = new UpdatesListenerService(telegramBot);
        telegramBot.setUpdatesListener(botUpdatesListenerService);

    }

}
