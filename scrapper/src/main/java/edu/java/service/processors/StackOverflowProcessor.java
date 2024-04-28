package edu.java.service.processors;

import edu.java.clients.StackOverflowClient;
import edu.java.domain.dto.StackOverflowDto.ItemsDto;
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
            String id = pathSegments[pathSegments.length - 2];
            ItemsDto itemsDto = stackOverflowClient.getQuestions(id).block();
            return itemsDto.getItems().getFirst().getLastEditDate().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getNameSite() {
        return "stackoverflow.com";
    }

}
