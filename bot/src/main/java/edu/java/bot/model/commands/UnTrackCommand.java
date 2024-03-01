package edu.java.bot.model.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnTrackCommand implements Command {
    private static final String COMMAND_NAME = "/untrack";
    private static final String DESCRIPTION = "Прекращает отслеживание ссылки";
    private static final String MISUSE = "После команды /untrack должна быть ссылка на сайт";
    private static final String DELETE_SITE = "Сайт больше не отслеживается";
    private static final String MISSING_SITE = "Сайт не отслеживался";
    @Autowired
    UserRepository userRepository;

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
        String text;

        String request = update.message().text();
        if (!check(request)) {
            return new SendMessage(id, MISUSE);
        }
        String[] list = request.split(" ");
        if (userRepository.siteInRepository(list[1])) {
            /*
             * Удаляем из репозитория этот сайт
             * */
            text = DELETE_SITE;
        } else {
            text = MISSING_SITE;
        }
        return new SendMessage(id, text);
    }

    @Override
    public boolean check(String request) {
        return (request.split(" ").length == 2);
    }
}
