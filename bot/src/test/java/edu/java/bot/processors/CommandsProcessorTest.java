package edu.java.bot.processors;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommandsProcessorTest {
    @Autowired
    public CommandsProcessor commandsProcessor;
//    @Mock
//    private List<? extends Command> listCommands;
    @Test
    void process() {
        var chat = mock(Chat.class);
        when(chat.id()).thenReturn(1L);

        var messageMock = mock(Message.class);
        when(messageMock.chat()).thenReturn(chat);
        when(messageMock.text()).thenReturn("/track https://github.com/sanyarnd/tinkoff-java-course-2023/");

//        var commandsProcessor = mock(CommandsProcessor.class);

//        listCommands =List.of(new UnTrackCommand(), new HelpCommand(), new ListCommand(), new StartCommand(), new TrackCommand());

        var updateMock = mock(Update.class);
        when(updateMock.message()).thenReturn(messageMock);

        var res = commandsProcessor.process(updateMock).toString();
        System.out.println(res);
//        Assert.assertEquals(
//            commandsProcessor.process(updateMock).toString(),
//            "Сайт добавлен"
//        );
//        Assertions.assertEquals(commandsProcessor.process(updateMock).toString(), "Сайт добавлен");
    }
}
