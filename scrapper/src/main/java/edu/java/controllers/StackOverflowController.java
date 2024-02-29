package edu.java.controllers;

import edu.java.clients.StackOverflowClient;
import org.springframework.stereotype.Controller;

@Controller
public class StackOverflowController {
    private StackOverflowClient stackOverflowClient;

    public StackOverflowController(StackOverflowClient stackOverflowClient) {
        this.stackOverflowClient = stackOverflowClient;
    }
}
