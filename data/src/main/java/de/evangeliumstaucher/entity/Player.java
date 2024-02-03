package de.evangeliumstaucher.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public record Player(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        Long id,
        String name
) {

}
