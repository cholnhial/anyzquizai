package dev.chol.anyquizai.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(of = {"nickName"})
public class Score {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String countryCode;

    @Column
    private String nickName;

    @Column
    private Integer totalCorrect;

    @Column
    private Float score;
}
