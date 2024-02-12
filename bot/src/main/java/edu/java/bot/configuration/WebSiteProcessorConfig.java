package edu.java.bot.configuration;

import edu.java.bot.processors.url.GitHubProcessor;
import edu.java.bot.processors.url.StackOverflowProcessor;
import edu.java.bot.processors.url.UrlProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class WebSiteProcessorConfig {
    private UrlProcessor urlProcessor;
    public WebSiteProcessorConfig(){
        urlProcessor = new GitHubProcessor();
        urlProcessor.setNext(new StackOverflowProcessor());
    }

    public UrlProcessor getUrlProcessor(){
        return urlProcessor;
    }
}
