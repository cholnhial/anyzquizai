package dev.chol.anyquizai.domain;

import dev.chol.anyquizai.domain.enumeration.Difficulty;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(of = {"uniqueCode"})
public class Quiz {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String uniqueCode;

    @Column
    private String title;

    @Column
    private Integer totalQuestions;

    @Column
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Column
    private ZonedDateTime created;
}
