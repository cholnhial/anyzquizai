package dev.chol.anyquizai.domain.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(of = {"title","correctAnswerLetter"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    private Quiz quiz;

    @Column
    private String title;

    @Column
    private String correctAnswerLetter;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<Answer> answers;
}
