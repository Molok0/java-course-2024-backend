package edu.java.bot.retry;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
@SuppressWarnings(value = {"NeedBraces", "LambdaBodyLength", "EmptyBlock", "CatchParameterName", "EmptyLineSeparator",
"LineLength", "MagicNumber", "NestedIfDepth"})
public class LinearRetry extends Retry {
    public final Duration minBackoff;
    public final Duration maxBackoff;
    public final long maxAttempts;
    public final boolean isTransientErrors;
    public final double jitterFactor;

    public final double hundred = 100.0;
    public final double magic = 9.223372036854776E18;

    public LinearRetry(
        Duration minBackoff, Duration maxBackoff, long maxAttempts, boolean isTransientErrors,
        double jitterFactor
    ) {
        this.minBackoff = minBackoff;
        this.maxBackoff = maxBackoff;
        this.maxAttempts = maxAttempts;
        this.isTransientErrors = isTransientErrors;
        this.jitterFactor = jitterFactor;
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
                long iteration = this.isTransientErrors ? copy.totalRetriesInARow() : copy.totalRetries();
                if (currentFailure == null) {
                    return Mono.error(new IllegalStateException("Retry.RetrySignal#failure() not expected to be null"));
                } else if (iteration >= this.maxAttempts) {
                } else {
                    Duration nextBackoff;
                    try {
                        nextBackoff = this.minBackoff.multipliedBy((long) Math.pow(2.0, (double) iteration));
                        if (nextBackoff.compareTo(this.maxBackoff) > 0) {
                            nextBackoff = this.maxBackoff;
                        }
                    } catch (ArithmeticException myVar) {
                        nextBackoff = this.maxBackoff;
                    }
                    if (nextBackoff.isZero()) {
                        return null;
                    } else {
                        ThreadLocalRandom random = ThreadLocalRandom.current();
                        long jitterOffset;
                        try {
                            jitterOffset =
                                nextBackoff.multipliedBy((long) (hundred * this.jitterFactor)).dividedBy(100L).toMillis();
                        } catch (ArithmeticException myVar) {
                            jitterOffset = Math.round(magic * this.jitterFactor);
                        }
                        long lowBound = Math.max(this.minBackoff.minus(nextBackoff).toMillis(), -jitterOffset);
                        long highBound = Math.min(this.maxBackoff.minus(nextBackoff).toMillis(), jitterOffset);
                        long jitter;
                        if (highBound == lowBound) {
                            if (highBound == 0L) jitter = 0L;
                            else jitter = random.nextLong(highBound);
                        } else jitter = random.nextLong(lowBound, highBound);
                        Duration effectiveBackoff = nextBackoff.plusMillis(jitter);
                        return null;
                    }
                }
                return null;
            });
        });

    }
}
