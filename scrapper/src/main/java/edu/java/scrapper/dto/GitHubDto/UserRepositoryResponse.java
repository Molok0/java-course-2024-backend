package edu.java.scrapper.dto.GitHubDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class UserRepositoryResponse {
    public Long id;
    @JsonProperty(value = "pushed_at")
    private OffsetDateTime lastUpdate;
}
