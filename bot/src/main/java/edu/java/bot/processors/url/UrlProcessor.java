package edu.java.bot.processors.url;

import org.springframework.stereotype.Component;

@Component
public abstract class UrlProcessor {
    private UrlProcessor next;

    public void setNext(UrlProcessor urlProcessor) {
        this.next = urlProcessor;
    }

    public abstract String handle(String url);

    public abstract String getNameSite();
}
