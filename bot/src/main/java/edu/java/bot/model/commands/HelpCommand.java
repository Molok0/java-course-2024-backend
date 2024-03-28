package edu.java.bot.model.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelpCommand implements Command {
    private static final String COMMAND_NAME = "/help";
    private static final String DESCRIPTION = "Выводит окно с командами";
    private static final String MISUSE = "Неправильное использование команды /help";
    private List<? extends Command> commands;

    @Autowired
    public void setCommands(List<? extends Command> listCommands) {
        this.commands = listCommands;
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
        StringBuilder stringBuilder = new StringBuilder();

        if (!check(update.message().text())) {
            return new SendMessage(id, MISUSE);
        }

        for (var command : commands) {
            stringBuilder.append(command.command()).append("\n");
        }

        return new SendMessage(id, stringBuilder.toString());
    }

    @Override
    public boolean check(String request) {
        return (request.split(" ").length == 1);
    }
}
