package edu.java.scrapper.dto.StackOverflowDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import lombok.Data;

@Data
//@JsonDeserialize(using = QuestionsResponseDeserializer.class)
public class QuestionsResponse {

    String title;
    @JsonProperty(value = "last_edit_date")
    OffsetDateTime lastEditDate;
    @JsonProperty(value = "last_activity_date")
    OffsetDateTime lastActivityDate;
    @JsonProperty(value = "creation_date")
    OffsetDateTime creationDate;
}
