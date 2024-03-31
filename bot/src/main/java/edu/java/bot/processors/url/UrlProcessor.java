package edu.java.bot.processors.url;

import org.springframework.stereotype.Component;

@Component
public abstract class UrlProcessor {
    protected UrlProcessor next;
    protected String nameSite;

    public void setNext(UrlProcessor urlProcessor) {
        this.next = urlProcessor;
    }

    public UrlProcessor getNext() {
        return this.next;
    }

    public abstract String handle(String url);

    public String getNameSite() {
        return this.nameSite;
    }
}
