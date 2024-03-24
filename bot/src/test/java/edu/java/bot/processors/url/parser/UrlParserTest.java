package edu.java.bot.processors.url.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

public class UrlParserTest {
    @InjectMocks
    private UrlParser urlParser;

    @Test
    void getWebSiteNameGitHub() {
        String fullUrlGitHub = "https://github.com/sanyarnd/tinkoff-java-course-2023/";
        String shortUrlGitHub = "github.com";
        Assertions.assertEquals(urlParser.getWebSiteName(fullUrlGitHub), shortUrlGitHub);
    }

    @Test
    void getWebSiteNameStackOverflow() {
        String fullUrlStackOverflow = "https://stackoverflow.com/search?q=unsupported%20link";
        String shortStackOverflow = "stackoverflow.com";
        Assertions.assertEquals(urlParser.getWebSiteName(fullUrlStackOverflow), shortStackOverflow);
    }
}
