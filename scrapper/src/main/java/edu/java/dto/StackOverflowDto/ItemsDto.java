package edu.java.dto.StackOverflowDto;

import java.util.List;
import lombok.Data;

@Data
public class ItemsDto {
    private List<QuestionsResponse> items;
}
