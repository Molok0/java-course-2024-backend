package edu.java.scrapper.dto.StackOverflowDto;

import java.util.List;
import lombok.Data;

@Data
public class ItemsDto {
    private List<QuestionsResponse> items;
}
