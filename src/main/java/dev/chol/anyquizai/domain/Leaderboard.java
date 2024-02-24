package dev.chol.anyquizai.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Leaderboard {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String countryCode;

    @Column
    private String nickName;

    @Column
    private Long totalCorrect;

    @Column
    private Float score;
}
