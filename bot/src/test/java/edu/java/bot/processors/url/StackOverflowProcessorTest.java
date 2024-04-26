package edu.java.bot.processors.url;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StackOverflowProcessorTest {
    @InjectMocks
    private StackOverflowProcessor stackOverflowProcessor;

    @Test
    void handleTrue() {
        String url = "https://stackoverflow.com/search?q=unsupported%20link";
        Assertions.assertEquals(stackOverflowProcessor.handle(url), url);
    }
    @Test
    void handleFalse() {
        String url = "https://github.com/sanyarnd/tinkoff-java-course-2023/";
        Assertions.assertNotEquals(stackOverflowProcessor.handle(url), url);
    }
}
