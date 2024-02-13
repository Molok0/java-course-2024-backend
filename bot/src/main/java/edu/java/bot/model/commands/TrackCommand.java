package edu.java.bot.model.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.services.WebSiteProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrackCommand implements Command {
    @Autowired
    public WebSiteProcessorService webSiteProcessorService;

    @Override
    public String command() {
        return "/track";
    }

    @Override
    public String description() {
        return new String("Начинает отслеживать ссылку");
    }

    @Override
    public SendMessage handle(Update update) {

        Long id = update.message().chat().id();
        String request = update.message().text();
        if (!check(request)) {
            return new SendMessage(id, "После команды /track должна быть ссылка на сайт");
        }
        String[] list = request.split(" ");
        try {
            String text = webSiteProcessorService.getUrlProcessor().handle(list[1]);
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
