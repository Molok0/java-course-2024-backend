package edu.java.scrapper.controllers;

import edu.java.scrapper.clients.GitHubClient;
import org.springframework.stereotype.Controller;

@Controller
public class GitHubController {
    private GitHubClient gitHubClient;

    public GitHubController(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

}
