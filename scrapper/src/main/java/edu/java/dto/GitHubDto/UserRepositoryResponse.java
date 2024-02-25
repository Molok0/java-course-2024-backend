package edu.java.dto.GitHubDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class UserRepositoryResponse {
    private Long id;
    @JsonProperty(value = "pushed_at")
    private OffsetDateTime lastUpdate;
}
