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
    @Autowired
    UserRepository userRepository;

    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String description() {
        return new String("Запускает бот");
    }

    @Override
    public SendMessage handle(Update update) {
        Long id = update.message().chat().id();
        if (!check(update.message().text())) {
            return new SendMessage(id, "Неправильное использование команды /start");
        }
        var user = userRepository.findById(id);
        String text;
        if (Objects.isNull(user)) {
            text = "Пользователь зарегестрирован";
            userRepository.addUser(id, new User(id));
        } else {
            text = "Пользователь уже был зарегестрирован";
        }
        return new SendMessage(id, text);
    }

    @Override
    public boolean check(String request) {
        return (request.split(" ").length == 1);
    }
}
