package edu.java.processors;

import edu.java.clients.GitHubClient;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GitHubProcessor extends UrlProcessor {
    private GitHubClient gitHubClient;
    private static final int PATH_COMPONENT3 = 3;
    private static final int PATH_COMPONENT2 = 2;

    public GitHubProcessor(UrlProcessor next, GitHubClient gitHubClient) {
        super(next);
        this.gitHubClient = gitHubClient;
    }

    @Override
    public String getLastChanges(String urlString) {
        try {
            URI uri = new URI(urlString);
            String[] pathComponents = uri.getPath().split("/");
            String username = pathComponents[PATH_COMPONENT2];
            String repoName = pathComponents[PATH_COMPONENT3];
            return username + repoName;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getNameSite() {
        return "github.com";
    }

}
