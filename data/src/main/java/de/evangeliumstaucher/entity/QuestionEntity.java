package de.evangeliumstaucher.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "game_entity_id", nullable = false)
    private GameEntity gameEntity;

    private Integer questionIndex;

    private String verseId;
}