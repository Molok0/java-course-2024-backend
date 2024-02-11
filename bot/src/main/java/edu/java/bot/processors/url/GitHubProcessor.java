package edu.java.bot.processors.url;

import java.net.URI;

public class GitHubProcessor extends UrlProcessor {
    public GitHubProcessor() {
        super();
    }

    @Override
    void handle(URI uri) {
        System.out.println("StackOverflowProcessor" + uri.toString());
    }
}
