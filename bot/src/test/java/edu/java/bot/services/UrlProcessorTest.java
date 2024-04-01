package edu.java.bot.services;

import com.pengrad.telegrambot.TelegramBot;
import edu.java.bot.BotApplication;
import edu.java.bot.processors.url.UrlProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = BotApplication.class)
class UrlProcessorTest {
    @MockBean TelegramBot telegramBot;
    @Autowired
    public UrlProcessor urlProcessor;

    @Test
    void getUrlProcessorTest() {
        Assertions.assertEquals(urlProcessor.getNameSite(), "github.com");
    }

    @Test
    void getNextUrlProcessorTest() {
        Assertions.assertEquals(urlProcessor.getNext().getNameSite(),"stackoverflow.com");
    }
}
