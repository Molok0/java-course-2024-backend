package edu.java.bot.api.services;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.LinkUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Service
public class BotService {
    private final TelegramBot telegramBot;

    public BotService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public HttpStatusCode sendUpdate(LinkUpdate linkUpdate) {
        var tgChatsId = linkUpdate.getTgChatIds();
        for (var id : tgChatsId) {
            String message = "Ссылка: " + linkUpdate.getUrl() + "\n" + "Сообщение: " + linkUpdate.getDescription();
            telegramBot.execute(new SendMessage(id, message));
        }
        return HttpStatus.OK;
    }
}
