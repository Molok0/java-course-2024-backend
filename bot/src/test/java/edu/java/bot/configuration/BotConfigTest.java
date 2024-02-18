package edu.java.bot.configuration;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BotConfigTest {
    @InjectMocks
    private BotConfig botConfig;
    @Mock
    private ApplicationConfig applicationConfig;

    @Test
    public void telegramBotTest()  {
        Assertions.assertNotNull(botConfig.telegramBot(applicationConfig));
    }
}

