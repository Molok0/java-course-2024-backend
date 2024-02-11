package edu.java.bot.services;

import com.pengrad.telegrambot.TelegramBot;
import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Bot {

    private final ApplicationConfig applicationConfig;
    private UserRepository userRepository;
    @Autowired
    public Bot(ApplicationConfig applicationConfig, UserRepository userRepository) {
        this.applicationConfig = applicationConfig;
        this.userRepository = userRepository;
    }
    @PostConstruct
    public void run() {
        TelegramBot telegramBot = new TelegramBot(applicationConfig.telegramToken());
        UpdatesListenerService botUpdatesListenerService = new UpdatesListenerService(telegramBot);
        telegramBot.setUpdatesListener(botUpdatesListenerService);
    }

}
