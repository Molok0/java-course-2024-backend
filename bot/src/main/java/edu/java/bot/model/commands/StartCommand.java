package edu.java.bot.model.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.model.User;
import edu.java.bot.repositories.UserRepository;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartCommand implements Command {
    private static final String COMMAND_NAME = "/start";
    private static final String DESCRIPTION = "Запускает бот";
    private static final String MISUSE = "Неправильное использование команды /start";
    private static final String USER_REGISTRATION = "Пользователь зарегестрирован";
    private static final String USER_HAS_BEEN_REGISTERED = "Пользователь уже был зарегестрирован";
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
        if (!check(update.message().text())) {
            return new SendMessage(id, MISUSE);
        }
        var user = userRepository.findById(id);
        String text;
        if (Objects.isNull(user)) {
            text = USER_REGISTRATION;
            userRepository.addUser(id, new User(id));
        } else {
            text = USER_HAS_BEEN_REGISTERED;
        }
        return new SendMessage(id, text);
    }

    @Override
    public boolean check(String request) {
        return (request.split(" ").length == 1);
    }
}
