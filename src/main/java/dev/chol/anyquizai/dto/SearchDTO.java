package dev.chol.anyquizai.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.annotations.Setting;

@Getter
@Setter
@Builder
public class SearchDTO {

    private String title;

    private Integer numberOfQuestions;

    private String difficulty;

    private String categoryId;

    private Sort.Direction sortByCreatedDate;

    private Sort.Direction sortByDifficulty;
}
