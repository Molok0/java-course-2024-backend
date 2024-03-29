package edu.java.bot.services;

import edu.java.bot.processors.url.GitHubProcessor;
import edu.java.bot.processors.url.StackOverflowProcessor;
import edu.java.bot.processors.url.UrlProcessor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class WebSiteProcessorService {
    private final UrlProcessor urlProcessor;

    public WebSiteProcessorService() {
        urlProcessor = new GitHubProcessor();
        urlProcessor.setNext(new StackOverflowProcessor());
        urlProcessor.getNext().setNext(null);
    }
}
