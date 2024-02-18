//package edu.java.bot.processors;
//
//import com.pengrad.telegrambot.model.Chat;
//import com.pengrad.telegrambot.model.Message;
//import com.pengrad.telegrambot.model.Update;
//import com.pengrad.telegrambot.request.SendMessage;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class CommandsProcessorTest {
//    @Autowired
//    public CommandsProcessor commandsProcessor;
//    private Pattern pattern = Pattern.compile("text\":\"([^\"]+)");
//
//    @Test
//    public void processTrackTrue() {
//        var chat = mock(Chat.class);
//        when(chat.id()).thenReturn(1L);
//
//        var messageMock = mock(Message.class);
//        when(messageMock.chat()).thenReturn(chat);
//        when(messageMock.text()).thenReturn("/track https://github.com/sanyarnd/tinkoff-java-course-2023/");
//
//        var updateMock = mock(Update.class);
//        when(updateMock.message()).thenReturn(messageMock);
//
//        SendMessage sendMessage = commandsProcessor.process(updateMock);
//
//        Matcher matcher = pattern.matcher(sendMessage.toWebhookResponse());
//        List<String> nameRuList = new ArrayList<>();
//
//        while (matcher.find()) {
//            nameRuList.add(matcher.group(1));
//        }
//
//        Assertions.assertEquals(
//            nameRuList.getFirst(),
//            "GitHubProcessor\\thttps://github.com/sanyarnd/tinkoff-java-course-2023/"
//        );
//    }
//
//    @Test
//    public void processTrackNot() {
//        var chat = mock(Chat.class);
//        when(chat.id()).thenReturn(1L);
//
//        var messageMock = mock(Message.class);
//        when(messageMock.chat()).thenReturn(chat);
//        when(messageMock.text()).thenReturn("/track 1");
//
//        var updateMock = mock(Update.class);
//        when(updateMock.message()).thenReturn(messageMock);
//
//        SendMessage sendMessage = commandsProcessor.process(updateMock);
//
//        Matcher matcher = pattern.matcher(sendMessage.toWebhookResponse());
//        List<String> nameRuList = new ArrayList<>();
//
//        while (matcher.find()) {
//            nameRuList.add(matcher.group(1));
//        }
//
//        Assertions.assertEquals(nameRuList.getFirst(), "Такой сайт не может отслеживаться");
//    }
//
//    @Test
//    public void processHelp() {
//        var chat = mock(Chat.class);
//        when(chat.id()).thenReturn(1L);
//
//        var messageMock = mock(Message.class);
//        when(messageMock.chat()).thenReturn(chat);
//        when(messageMock.text()).thenReturn("/help");
//
//        var updateMock = mock(Update.class);
//        when(updateMock.message()).thenReturn(messageMock);
//
//        SendMessage sendMessage = commandsProcessor.process(updateMock);
//
//        Matcher matcher = pattern.matcher(sendMessage.toWebhookResponse());
//        List<String> nameRuList = new ArrayList<>();
//
//        while (matcher.find()) {
//            nameRuList.add(matcher.group(1));
//        }
//
//        Assertions.assertEquals(nameRuList.getFirst(), "/list\\n/start\\n/track\\n/untrack\\n");
//    }
//
//    @Test
//    public void processList() {
//        var chat = mock(Chat.class);
//        when(chat.id()).thenReturn(1L);
//
//        var messageMock = mock(Message.class);
//        when(messageMock.chat()).thenReturn(chat);
//        when(messageMock.text()).thenReturn("/list");
//
//        var updateMock = mock(Update.class);
//        when(updateMock.message()).thenReturn(messageMock);
//
//        SendMessage sendMessage = commandsProcessor.process(updateMock);
//
//        Matcher matcher = pattern.matcher(sendMessage.toWebhookResponse());
//        List<String> nameRuList = new ArrayList<>();
//
//        while (matcher.find()) {
//            nameRuList.add(matcher.group(1));
//        }
//
//        Assertions.assertEquals(nameRuList.getFirst(), "github.com\\nstackoverflow.com\\n");
//    }
//
//    @Test
//    public void processUntrack() {
//        var chat = mock(Chat.class);
//        when(chat.id()).thenReturn(1L);
//
//        var messageMock = mock(Message.class);
//        when(messageMock.chat()).thenReturn(chat);
//        when(messageMock.text()).thenReturn("/untrack https://github.com/sanyarnd/tinkoff-java-course-2023/");
//
//        var updateMock = mock(Update.class);
//        when(updateMock.message()).thenReturn(messageMock);
//
//        SendMessage sendMessage = commandsProcessor.process(updateMock);
//
//        Matcher matcher = pattern.matcher(sendMessage.toWebhookResponse());
//        List<String> nameRuList = new ArrayList<>();
//
//        while (matcher.find()) {
//            nameRuList.add(matcher.group(1));
//        }
//
//        Assertions.assertEquals(nameRuList.getFirst(), "Сайт больше не отслеживается");
//    }
//
//}
