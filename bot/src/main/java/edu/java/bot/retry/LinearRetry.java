package edu.java.bot.retry;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

public class LinearRetry extends Retry {

    @Override
    public Publisher<?> generateCompanion(Flux<RetrySignal> flux) {
        return null;
    }
}
