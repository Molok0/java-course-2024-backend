package edu.java.processors;

import edu.java.clients.StackOverflowClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StackOverflowProcessor extends UrlProcessor {
    private StackOverflowClient stackOverflowClient;

    public StackOverflowProcessor(UrlProcessor next, StackOverflowClient stackOverflowClient) {
        super(next);
        this.stackOverflowClient = stackOverflowClient;
    }

    @Override
    public String getNameSite() {
        return "stackoverflow.com";
    }

}
