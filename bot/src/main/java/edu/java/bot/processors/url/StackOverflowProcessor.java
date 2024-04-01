package edu.java.bot.processors.url;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StackOverflowProcessor extends UrlProcessor {

    public StackOverflowProcessor(UrlProcessor next) {
        super(next);
    }

    @Override
    public String getNameSite() {
        return "stackoverflow.com";
    }

}
