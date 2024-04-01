package edu.java.bot.model.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.processors.url.UrlProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListCommand implements Command {
    private static final String COMMAND_NAME = "/list";
    private static final String DESCRIPTION = "Возвращает список всех отслеживаемых сайтов";
    private static final String MISUSE = "Неправильное использование команды /list";
    private UrlProcessor urlProcessors;

    @Autowired
    public void setCommands(UrlProcessor urlProcessors) {
        this.urlProcessors = urlProcessors;
    }

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
        StringBuilder stringBuilder = new StringBuilder();

        if (!check(update.message().text())) {
            return new SendMessage(id, MISUSE);
        }
        stringBuilder.append(urlProcessors.getNameSite()).append("\n");
        while (urlProcessors.getNext() != null) {
            stringBuilder.append(urlProcessors.getNext().getNameSite()).append("\n");
            urlProcessors = urlProcessors.getNext();
        }

        return new SendMessage(id, stringBuilder.toString());
    }

    @Override
    public boolean check(String request) {
        return (request.split(" ").length == 1);
    }
}
