package edu.java.domain.dto.StackOverflowDto;

import java.util.List;
import lombok.Data;

@Data
public class ItemsDto {
    private List<QuestionsResponse> items;
}
