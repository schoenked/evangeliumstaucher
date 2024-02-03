package de.evangeliumstaucher.entity;

import jakarta.persistence.*;

@Entity
public record Game(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        Long id,

        String name,

        String description
) {
}