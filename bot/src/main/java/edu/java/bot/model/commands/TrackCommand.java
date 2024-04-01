package edu.java.bot.model.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.processors.url.UrlProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TrackCommand implements Command {
    private static final String COMMAND_NAME = "/track";
    private static final String DESCRIPTION = "Начинает отслеживать ссылку";
    private static final String MISUSE = "После команды /track должна быть ссылка на сайт";
    public UrlProcessor urlProcessor;

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
        try {
            String text = urlProcessor.handle(list[1]);
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
