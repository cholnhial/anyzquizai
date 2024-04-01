package dev.chol.anyquizai.domain.jpa;

import dev.chol.anyquizai.dto.ScoreDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(of = {"nickName"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Score {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    private Quiz quiz;

    @Column
    private String countryCode;

    @Column
    private String nickName;

    @Column
    private Integer totalCorrect;

    @Column
    private Float score;

    public ScoreDTO toScoreDTO() {
        return new ScoreDTO(nickName, totalCorrect, quiz.getTotalQuestions(),score, countryCode);
    }
}
