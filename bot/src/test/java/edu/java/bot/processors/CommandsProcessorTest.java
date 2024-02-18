package edu.java.bot.processors;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import edu.java.bot.model.commands.Command;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommandsProcessorTest {
    @InjectMocks
    private CommandsProcessor commandsProcessor;
    @Mock
    private List<? extends Command> listCommands;

    @Test
    void process() {
//        var chat = mock(Chat.class);
//        when(chat.id()).thenReturn(1L);
//
//        var messageMock = mock(Message.class);
//        when(messageMock.chat()).thenReturn(chat);
//        when(messageMock.text()).thenReturn("/track https://github.com/sanyarnd/tinkoff-java-course-2023/");
//
////        var commandsProcessor = mock(CommandsProcessor.class);
//
////        listCommands =List.of(new UnTrackCommand(), new HelpCommand(), new ListCommand(), new StartCommand(), new TrackCommand());
//
//        var updateMock = mock(Update.class);
//        when(updateMock.message()).thenReturn(messageMock);
//
//        Assertions.assertEquals(commandsProcessor.process(updateMock), "Сайт добавлен");
    }
}
