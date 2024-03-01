package edu.java.controllers;

import edu.java.clients.GitHubClient;
import org.springframework.stereotype.Controller;

@Controller
public class GitHubController {
    private GitHubClient gitHubClient;

    public GitHubController(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
        gitHubClient.getInfo("Molok0", "java-course-2024-backend").subscribe(System.out::println);
    }

}
