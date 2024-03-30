package de.evangeliumstaucher.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class GameSessionEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        PlayerEntity player;

        @ManyToOne(fetch = FetchType.LAZY)
        GameEntity game;

}