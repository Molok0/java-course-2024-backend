package edu.java.bot.model.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelpCommand implements Command {
    private List<? extends Command> commands;

    @Autowired
    public void setCommands(List<? extends Command> listCommands) {
        this.commands = listCommands;
    }

    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String description() {
        return new String("Выводит окно с командами");
    }

    @Override
    public SendMessage handle(Update update) {
        Long id = update.message().chat().id();
        StringBuilder stringBuilder = new StringBuilder();

        if (!check(update.message().text())) {
            return new SendMessage(id, "Неправильное использование команды /start");
        }

        for (var command : commands) {
            stringBuilder.append(command.command() + "\n");
        }

        return new SendMessage(id, stringBuilder.toString());
    }

    @Override
    public boolean check(String request) {
        return (request.split(" ").length == 1);
    }
}
