package edu.java.bot.model.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class UnTrackCommand implements Command {
    @Override
    public String command() {
        return "/untrack";
    }

    @Override
    public String description() {
        return new String("Прекращает отслеживание ссылки");
    }

    @Override
    public SendMessage handle(Update update) {
        return null;
    }
}
