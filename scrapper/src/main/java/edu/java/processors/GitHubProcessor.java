package edu.java.processors;

import edu.java.configuration.ClientConfig;
import edu.java.processors.parser.UrlParser;
import org.springframework.stereotype.Component;

@Component
public class GitHubProcessor extends UrlProcessor {
    private final String nameSite = "github.com";
    private ClientConfig clientConfig;

    public GitHubProcessor(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    @Override
    public String handle(String url) {
        String text;
        if (UrlParser.getWebSiteName(url).equals(nameSite)) {
            text = url;
        } else if (next != null) {
            text = next.handle(url);
        } else {
            text = "Такой сайт не может отслеживаться";
        }
        return text;
    }
}
