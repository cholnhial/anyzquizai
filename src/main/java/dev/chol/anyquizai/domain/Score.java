package dev.chol.anyquizai.domain;

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
}
