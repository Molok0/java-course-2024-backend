package edu.java.bot.model.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.api.client.ScrapperClient;
import edu.java.bot.api.dto.AddLinkRequest;
import edu.java.bot.processors.url.UrlProcessor;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TrackCommand implements Command {
    private static final String COMMAND_NAME = "/track";
    private static final String DESCRIPTION = "Начинает отслеживать ссылку";
    private static final String MISUSE = "После команды /track должна быть ссылка на сайт";
    private static final String NOT_COMMAND = "Такой сайт не может отслеживаться";
    private static final String ACCEPTED = "Сслыка добавлена в отслеживаемые";
    private static final String ERROR = "Ошибка добавления";
    public UrlProcessor urlProcessor;

    @Autowired
    ScrapperClient scrapperClient;

    @Autowired
    public TrackCommand(UrlProcessor urlProcessor) {
        this.urlProcessor = urlProcessor;
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
        String request = update.message().text();
        if (!check(request)) {
            return new SendMessage(id, MISUSE);
        }
        String[] list = request.split(" ");
        var url = list[1];
        try {

            String text = urlProcessor.handle(url);

            if (!text.equals(NOT_COMMAND)) {

                AddLinkRequest addLinkRequest = new AddLinkRequest();
                addLinkRequest.setLink(URI.create(url));
                log.info(url);
                try {
                    scrapperClient.postLinks(addLinkRequest, id).block();
                    text = ACCEPTED;
                } catch (Exception e) {
                    text = ERROR;
                }

            }

            return new SendMessage(id, text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean check(String request) {
        return (request.split(" ").length == 2);
    }
}
