package edu.java.bot.model.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.api.client.ScrapperClient;
import edu.java.bot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartCommand implements Command {
    private static final String COMMAND_NAME = "/start";
    private static final String DESCRIPTION = "Запускает бот";
    private static final String MISUSE = "Неправильное использование команды /start";
    private static final String USER_REGISTRATION = "Пользователь зарегестрирован";
    @Autowired
    UserRepository userRepository;
    @Autowired
    ScrapperClient scrapperClient;

    @Override
    public String command() {
        return COMMAND_NAME;
    }

    @Override
    public String description() {
        return DESCRIPTION;
    }

    @Override
    public SendMessage handle(Update update) {
        Long id = update.message().chat().id();
        if (!check(update.message().text())) {
            return new SendMessage(id, MISUSE);
        }

        String text;

        text = USER_REGISTRATION;
        scrapperClient.regChat(id);

        return new SendMessage(id, text);
    }

    @Override
    public boolean check(String request) {
        return (request.split(" ").length == 1);
    }
}
