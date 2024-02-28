package edu.java.scrapper.controllers;

import edu.java.scrapper.clients.StackOverflowClient;
import org.springframework.stereotype.Controller;

@Controller
public class StackOverflowController {
    private StackOverflowClient stackOverflowClient;

    public StackOverflowController(StackOverflowClient stackOverflowClient) {
        this.stackOverflowClient = stackOverflowClient;
    }
}
