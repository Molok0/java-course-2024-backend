package edu.java.bot.model.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.configuration.WebSiteProcessorConfig;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrackCommand implements Command {
    @Autowired
    public WebSiteProcessorConfig webSiteProcessorConfig;

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
        String[] list = request.split(" ");
        try {
            webSiteProcessorConfig.getUrlProcessor().handle(URI.create(list[1]));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        System.out.println(2);
        return new SendMessage(id, "Сайт добавлен");
    }
}
