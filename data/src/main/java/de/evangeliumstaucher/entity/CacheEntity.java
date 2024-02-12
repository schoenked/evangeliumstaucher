package de.evangeliumstaucher.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CacheEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String key;
    @Column(length = 100000)
    private String value;
    private Long expiry;

    // getters and setters
}
