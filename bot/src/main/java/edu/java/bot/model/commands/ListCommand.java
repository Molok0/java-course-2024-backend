package edu.java.bot.model.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class ListCommand implements Command{
    @Override
    public String command() {
        return "/list";
    }

    @Override
    public String description() {
        return new String("Возвращает список всех отслеживаемых сайтов");
    }

    @Override
    public SendMessage handle(Update update) {
        return null;
    }
}
