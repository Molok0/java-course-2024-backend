package edu.java.bot.retry;

import java.time.Duration;
import java.util.function.Predicate;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

public class LinearRetry extends Retry {
    public final Duration minBackoff;
    public final Duration maxBackoff;
    public final long maxAttempts;

    public LinearRetry(Duration minBackoff, Duration maxBackoff, long maxAttempts) {
        this.minBackoff = minBackoff;
        this.maxBackoff = maxBackoff;
        this.maxAttempts = maxAttempts;
    }

    public static LinearRetry filter(Predicate<? super Throwable> errorFilter) {
        return null;
    }

    public static LinearRetry maxBackoff(Duration maxBackoff) {
        return null;
    }

    @Override
    public Flux<Long> generateCompanion(Flux<RetrySignal> flux) {

        return null;
    }
}
