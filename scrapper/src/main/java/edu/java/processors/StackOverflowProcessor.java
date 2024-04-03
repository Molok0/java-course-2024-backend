package edu.java.processors;

import edu.java.clients.StackOverflowClient;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StackOverflowProcessor extends UrlProcessor {
    private StackOverflowClient stackOverflowClient;

    public StackOverflowProcessor(UrlProcessor next, StackOverflowClient stackOverflowClient) {
        super(next);
        this.stackOverflowClient = stackOverflowClient;
    }

    @Override
    public String getLastChanges(String urlString) {
        try {
            URI uri = new URI(urlString);
            String path = uri.getPath();
            String[] pathSegments = path.split("/");
            String id = pathSegments[pathSegments.length - 1];
            return id;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getNameSite() {
        return "stackoverflow.com";
    }

}
