package edu.java.bot.configuration;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.mock;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationConfigTest {
    @InjectMocks
    private ApplicationConfig applicationConfig;

    @Test
    public void getTokenTest() {
        Assertions.assertNull(applicationConfig.telegramToken());
    }
}
