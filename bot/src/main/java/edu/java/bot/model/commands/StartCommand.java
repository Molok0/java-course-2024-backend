package edu.java.bot.model.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.model.User;
import edu.java.bot.repositories.UserRepository;
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
        var user = userRepository.findById(id);
        String text;
        if (user.equals(null)){
            text = "Пользователь зарегестрирован";
            userRepository.addUser(id, new User(id));
        }else {
            text = "Пользователь уже был зарегестрирован";
        }
        return new SendMessage(update.message().chat().id(), "Пользователь зарегестрирован");
    }
}
