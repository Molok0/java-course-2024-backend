package edu.java.bot.model.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.api.client.ScrapperClient;
import edu.java.bot.api.dto.ListLinksResponse;
import edu.java.bot.api.dto.RemoveLinkRequest;
import edu.java.bot.repositories.UserRepository;
import java.net.URI;
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
        String text;

        String request = update.message().text();
        if (!check(request)) {
            return new SendMessage(id, MISUSE);
        }
        String[] list = request.split(" ");
        String uri = list[1];

        ListLinksResponse listLinksResponse = scrapperClient.getLinks(id).block();

        if (userRepository.siteInRepository(uri)) {
            /*
             * Удаляем из репозитория этот сайт
             * */
            RemoveLinkRequest removeLinkRequest = new RemoveLinkRequest();
            removeLinkRequest.setLink(URI.create(uri));

            scrapperClient.deleteLinks(removeLinkRequest, id);

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
