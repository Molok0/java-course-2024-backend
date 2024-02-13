package edu.java.bot.model.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.processors.url.UrlProcessor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListCommand implements Command {
    private List<? extends UrlProcessor> urlProcessors;

    @Autowired
    public void setCommands(List<? extends UrlProcessor> urlProcessors) {
        this.urlProcessors = urlProcessors;
    }

    @Override
    public String command() {
        return "/list";
    }

    @Override
    public String description() {
        return new String("Возвращает список всех отслеживаемых сайтов");
    }

    @Override
    public SendMessage handle(Update update) {
        Long id = update.message().chat().id();
        StringBuilder stringBuilder = new StringBuilder();

        if (!check(update.message().text())) {
            return new SendMessage(id, "Неправильное использование команды /start");
        }

        for (var urlProcessor : urlProcessors) {
            stringBuilder.append(urlProcessor.getNameSite() + "\n");
        }
        return new SendMessage(id, stringBuilder.toString());
    }

    @Override
    public boolean check(String request) {
        return (request.split(" ").length == 1);
    }
}
