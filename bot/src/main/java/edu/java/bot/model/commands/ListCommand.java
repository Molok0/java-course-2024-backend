package edu.java.bot.model.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.api.client.ScrapperClient;
import edu.java.bot.processors.url.UrlProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListCommand implements Command {
    private static final String COMMAND_NAME = "/list";
    private static final String DESCRIPTION = "Возвращает список всех отслеживаемых сайтов";
    private static final String MISUSE = "Неправильное использование команды /list";
    private static final String TEXT_TRACKED_LINKS = "Отслеживаемые ссылки:";
    private UrlProcessor urlProcessors;

    @Autowired
    ScrapperClient scrapperClient;

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
        stringBuilder.append(TEXT_TRACKED_LINKS).append("\n");

        var links = scrapperClient.getLinks(id).block();

        var uris = links.getLinks().stream().map(el -> el.getUrl().toString()).toList();

        for (var uri : uris) {
            stringBuilder.append(uri).append("\n");
        }
        return new SendMessage(id, stringBuilder.toString());
    }

    @Override
    public boolean check(String request) {
        return (request.split(" ").length == 1);
    }
}
