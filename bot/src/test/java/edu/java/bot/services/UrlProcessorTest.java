package edu.java.bot.services;

import edu.java.bot.BotApplication;
import edu.java.bot.processors.url.UrlProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BotApplication.class)
class UrlProcessorTest {
    @Autowired
    public UrlProcessor urlProcessor;

    @Test
    void getUrlProcessorTest() {
        Assertions.assertEquals(urlProcessor.getNameSite(), "github.com");
    }

    @Test
    void getNextUrlProcessorTest() {
        Assertions.assertEquals(urlProcessor.getNext().getNameSite(), "stackoverflow.com");
    }
}
