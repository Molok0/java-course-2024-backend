package edu.java.bot.retry;

import java.time.Duration;
import java.util.function.Predicate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

public class LinearRetry extends Retry {
    public final Duration minBackoff;
    public final Duration maxBackoff;
    public final long maxAttempts;
    public final boolean isTransientErrors;

    public LinearRetry(Duration minBackoff, Duration maxBackoff, long maxAttempts, boolean isTransientErrors) {
        this.minBackoff = minBackoff;
        this.maxBackoff = maxBackoff;
        this.maxAttempts = maxAttempts;
        this.isTransientErrors = isTransientErrors;
    }

    public static LinearRetry filter(Predicate<? super Throwable> errorFilter) {
        return null;
    }

    public static LinearRetry maxBackoff(Duration maxBackoff) {
        return null;
    }

    @Override
    public Flux<Long> generateCompanion(Flux<RetrySignal> flux) {
        return Flux.deferContextual((contextView) -> {
            return flux.contextWrite(contextView).concatMap((retryWhenState) -> {
                Retry.RetrySignal copy = retryWhenState.copy();
                Throwable currentFailure = copy.failure();
                if (currentFailure == null) {
                    return Mono.error(new IllegalStateException("Retry.RetrySignal#failure() not expected to be null"));
                }
                long iteration = this.isTransientErrors ? copy.totalRetriesInARow() : copy.totalRetries();

                return null;
            });
        });

    }
}
