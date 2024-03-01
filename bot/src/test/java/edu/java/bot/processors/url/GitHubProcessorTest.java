package edu.java.bot.processors.url;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GitHubProcessorTest {
    @InjectMocks
    private GitHubProcessor gitHubProcessor;

    @Test
    void handleTrue() {
        String url = "https://github.com/sanyarnd/tinkoff-java-course-2023/";
        Assertions.assertEquals(gitHubProcessor.handle(url), "GitHubProcessor\t" + url);
    }

    @Test
    void handleFalse() {
        String url = "https://stackoverflow.com/search?q=unsupported%20link";
        Assertions.assertNotEquals(gitHubProcessor.handle(url), "GitHubProcessor\t" + url);
    }
}
