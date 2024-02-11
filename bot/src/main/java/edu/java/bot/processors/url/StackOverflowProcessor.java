package edu.java.bot.processors.url;

import java.net.URI;
import java.util.logging.Logger;

public class StackOverflowProcessor extends UrlProcessor{

    public StackOverflowProcessor(){
        super();
    }
    @Override
    void handle(URI uri) {
        System.out.println("StackOverflowProcessor" + uri.toString());
    }
}
