package edu.java.bot.processors;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.model.commands.Command;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CommandsProcessor implements UserMessageProcessor {
    private List<? extends Command> commands;
    private static final String COMMAND_NOT_FOUND = "Команда не найдена";
    private static final String AN_EMPTY_LINE = "Вы отправили пустую строку";

    public CommandsProcessor(List<? extends Command> listCommands) {
        this.commands = listCommands;
    }

    @Override
    public SendMessage process(Update update) {
        String request = update.message().text();
        String[] list = request.split(" ");

        try {
            if (list.length == 0) {
                return new SendMessage(update.message().chat().id(), AN_EMPTY_LINE);
            }
            for (Command command : commands) {
                if (command.command().equals(list[0])) {
                    return command.handle(update);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new SendMessage(update.message().chat().id(), COMMAND_NOT_FOUND);
    }
}
