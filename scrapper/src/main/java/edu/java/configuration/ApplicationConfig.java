package edu.java.configuration;

import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(
    @NotNull
    @Bean
    Scheduler scheduler, AccessType databaseAccessType, UrlClient urlClient) {
    public record Scheduler(boolean enable, @NotNull Duration interval, @NotNull Duration forceCheckDelay) {
    }

    public record UrlClient(String githubDefaultUrl, String stackoverflowDefaultUrl, String botDefaultUrl) {
    }
    public enum AccessType {
        JDBC, JPA,
        JOOQ
    }

}
