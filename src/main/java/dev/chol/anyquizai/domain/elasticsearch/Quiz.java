package dev.chol.anyquizai.domain.elasticsearch;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Document(indexName = "quiz_index")
@Getter
@Setter
@Builder
public class Quiz {

    @Id
    private Long id;

    @Field
    private String title;

    @Field
    private Integer questions;

    @Field
    private String uniqueCode;

    @Field
    private String difficulty;

    @Field
    private Integer _difficulty;

    @Field
    private Long categoryId;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
    private LocalDateTime created;

}
