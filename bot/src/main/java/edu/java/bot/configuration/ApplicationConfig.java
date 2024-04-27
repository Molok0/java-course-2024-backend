package edu.java.bot.configuration;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(@NotEmpty String telegramToken, UrlClient urlClient, Retry retry) {
    public record UrlClient(String scrapperDefaultUrl) {
    }

    public record Retry(@NotNull int retryDelay, @NotNull int maxAttempts) {
    }
}
