package edu.java.bot.processors.url;

import java.net.URI;

public abstract class UrlProcessor {
    private UrlProcessor next;
    public void setNext(UrlProcessor urlProcessor){
        this.next = urlProcessor;
    }
    public abstract void handle(URI uri);

}
