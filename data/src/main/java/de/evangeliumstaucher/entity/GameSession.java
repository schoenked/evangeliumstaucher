package de.evangeliumstaucher.entity;

import jakarta.persistence.*;


@Entity
public record GameSession(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        Long id,

        @ManyToOne(fetch = FetchType.LAZY)
        Player player,
        @ManyToOne(fetch = FetchType.LAZY)
        Game game
) {
}