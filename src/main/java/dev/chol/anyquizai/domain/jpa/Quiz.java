package dev.chol.anyquizai.domain.jpa;

import dev.chol.anyquizai.dto.AnswerDTO;
import dev.chol.anyquizai.dto.QuestionDTO;
import dev.chol.anyquizai.dto.QuizDTO;
import dev.chol.anyquizai.enumeration.Difficulty;
import jakarta.persistence.*;
import lombok.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(of = {"uniqueCode"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String uniqueCode;

    @Column
    private String title;

    @Column
    private String photoPath;

    @Column
    private Integer totalQuestions;

    @Column
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Column
    private LocalDateTime created;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private Set<Question> questions;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private Set<Score> scores;

    @OneToOne
    private Category category;

    public QuizDTO toDTO() {
        return new QuizDTO(id,category.getId(), title, totalQuestions, difficulty, questions.stream()
                .map(question -> new QuestionDTO(question.getTitle(),
                        question.getCorrectAnswerLetter(),
                        question.getAnswers().stream()
                        .map(answer -> new AnswerDTO(answer.getLetter(), answer.getTitle())).toList())).toList(),created);
    }

    public byte[] getPhotoInBytes() throws IOException {
        return Files.readAllBytes(Paths.get(photoPath));
    }
}
