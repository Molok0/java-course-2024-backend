package edu.java.bot.model.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.api.client.ScrapperClient;
import edu.java.bot.api.dto.LinkResponse;
import edu.java.bot.api.dto.ListLinksResponse;
import edu.java.bot.api.dto.RemoveLinkRequest;
import edu.java.bot.repositories.UserRepository;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UnTrackCommand implements Command {
    private static final String COMMAND_NAME = "/untrack";
    private static final String DESCRIPTION = "Прекращает отслеживание ссылки";
    private static final String MISUSE = "После команды /untrack должна быть ссылка на сайт";
    private static final String DELETE_SITE = "Сайт больше не отслеживается. ";
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

        try {
            /*
             * Удаляем из репозитория этот сайт
             * */
            RemoveLinkRequest removeLinkRequest = new RemoveLinkRequest();
            removeLinkRequest.setLink(URI.create(uri));

            LinkResponse linkResponse = scrapperClient.deleteLinks(removeLinkRequest, id).block();
            text = DELETE_SITE + linkResponse.getUrl();
        } catch (Exception exception) {
            text = MISSING_SITE;
        }
        return new SendMessage(id, text);
    }

//    private boolean check(ListLinksResponse listLinksResponse, String uri) {
//        for (var el : listLinksResponse.getLinks()) {
//            log.info(String.valueOf(el));
//            log.info(String.valueOf(uri));
//            if (el.equals(uri)) {
//                log.info(String.valueOf(el));
//                return true;
//            }
//        }
//        return false;
//    }

    @Override
    public boolean check(String request) {
        return (request.split(" ").length == 2);
    }
}
