package edu.java.bot.processors;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.model.commands.Command;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CommandsProcessor implements UserMessageProcessor {
    private List<? extends Command> commands;

    public CommandsProcessor(List<? extends Command> listCommands) {
        this.commands = listCommands;
    }

    @Override
    public SendMessage process(Update update) {
        String request = update.message().text();
        String[] list = request.split(" ");

        try {
            for (Command command : commands) {
                if (command.command().equals(list[0])) {
                    return command.handle(update);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new SendMessage(update.message().chat().id(), "Команда не найдена");
    }
}
