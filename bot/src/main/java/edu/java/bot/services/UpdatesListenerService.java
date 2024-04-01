package edu.java.bot.services;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.processors.CommandsProcessor;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UpdatesListenerService implements UpdatesListener {
    private final TelegramBot telegramBot;
    private final CommandsProcessor commandsProcessor;

    public UpdatesListenerService(TelegramBot telegramBot, CommandsProcessor commandsProcessor) {
        this.commandsProcessor = commandsProcessor;
        this.telegramBot = telegramBot;
        this.telegramBot.setUpdatesListener(this);
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
