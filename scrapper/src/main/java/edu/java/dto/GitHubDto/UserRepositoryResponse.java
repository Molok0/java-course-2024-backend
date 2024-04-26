package edu.java.dto.GitHubDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public record UserRepositoryResponse(Long id, @JsonProperty(value = "pushed_at") OffsetDateTime lastUpdate) {
}
