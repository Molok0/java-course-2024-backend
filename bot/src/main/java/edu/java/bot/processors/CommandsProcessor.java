package edu.java.bot.processors;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.model.commands.Command;
import edu.java.bot.model.commands.HelpCommand;
import edu.java.bot.model.commands.ListCommand;
import edu.java.bot.model.commands.StartCommand;
import edu.java.bot.model.commands.TrackCommand;
import edu.java.bot.model.commands.UnTrackCommand;
import java.util.List;

public class CommandsProcessor implements UserMessageProcessor {
    private List<? extends Command> commands = commands();

    public CommandsProcessor() {

    }

    @Override
    public List<? extends Command> commands() {
        return List.of(
            new HelpCommand(),
            new ListCommand(),
            new StartCommand(),
            new TrackCommand(),
            new UnTrackCommand()
        );
    }

    @Override
    public SendMessage process(Update update) {
        String request = update.message().text();
        String[] list = request.split(" ");

        try {
            for (Command command : commands) {
                if (command.command() == list[0]) {
                    return command.handle(update);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            return new SendMessage(update.message().chat().id(), "Команда не найдена");
        }

    }
}
