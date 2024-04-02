package edu.java.processors;

import edu.java.clients.GitHubClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GitHubProcessor extends UrlProcessor {
    private GitHubClient gitHubClient;

    public GitHubProcessor(UrlProcessor next, GitHubClient gitHubClient) {
        super(next);
        this.gitHubClient = gitHubClient;
    }

    @Override
    public String getNameSite() {
        return "github.com";
    }

}
