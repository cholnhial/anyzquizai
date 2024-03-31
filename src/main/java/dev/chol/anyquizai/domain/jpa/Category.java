package dev.chol.anyquizai.domain.jpa;

import dev.chol.anyquizai.dto.CategoryDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public CategoryDTO toDTO () {
        return new CategoryDTO(id, name);
    }
}
