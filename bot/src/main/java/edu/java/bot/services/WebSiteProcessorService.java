package edu.java.bot.services;

import com.pengrad.telegrambot.model.BotCommand;
import edu.java.bot.processors.url.GitHubProcessor;
import edu.java.bot.processors.url.StackOverflowProcessor;
import edu.java.bot.processors.url.UrlProcessor;
import org.springframework.stereotype.Component;

@Component
public class WebSiteProcessorService {
    private UrlProcessor urlProcessor;

    public WebSiteProcessorService() {
        urlProcessor = new GitHubProcessor();
        urlProcessor.setNext(new StackOverflowProcessor());
    }

    public UrlProcessor getUrlProcessor() {
        return urlProcessor;
    }
}
