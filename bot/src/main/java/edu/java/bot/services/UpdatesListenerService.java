package edu.java.bot.services;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.processors.CommandsProcessor;
import java.util.List;

public class UpdatesListenerService implements UpdatesListener {
    private TelegramBot telegramBot;
    private CommandsProcessor commandsProcessor = new CommandsProcessor();

    public UpdatesListenerService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public int process(List<Update> list) {
        for (Update update : list) {
            SendMessage sendMessage = commandsProcessor.process(update);
            telegramBot.execute(sendMessage);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
