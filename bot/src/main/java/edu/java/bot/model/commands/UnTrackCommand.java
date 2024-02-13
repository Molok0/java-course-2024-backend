package edu.java.bot.model.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnTrackCommand implements Command {
    @Autowired
    UserRepository userRepository;

    @Override
    public String command() {
        return "/untrack";
    }

    @Override
    public String description() {
        return new String("Прекращает отслеживание ссылки");
    }

    @Override
    public SendMessage handle(Update update) {
        Long id = update.message().chat().id();
        String text;

        String request = update.message().text();
        if (!check(request)) {
            return new SendMessage(id, "После команды /untrack должна быть ссылка на сайт");
        }
        String[] list = request.split(" ");
        if (userRepository.siteInRepository(list[1])) {
            /*
             * Удаляем из репозитория этот сайт
             * */
            text = "Сайт больше не отслеживается";
        } else {
            text = "Сайт не отслеживался";
        }
        return new SendMessage(id, text);
    }

    @Override
    public boolean check(String request) {
        return (request.split(" ").length == 2);
    }
}
