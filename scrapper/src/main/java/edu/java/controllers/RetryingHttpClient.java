package edu.java.controllers;

import org.springframework.retry.RetryPolicy;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.client.HttpClientConfig;

public class RetryingHttpClient extends HttpClient {
    private RetryPolicy retryPolicy;

    public RetryingHttpClient(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
    }

    @Override
    public HttpClientConfig configuration() {
        return null;
    }

    @Override
    protected HttpClient duplicate() {
        return null;
    }
}
