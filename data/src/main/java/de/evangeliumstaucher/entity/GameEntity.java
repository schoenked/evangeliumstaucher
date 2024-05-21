package de.evangeliumstaucher.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    private String bibleId;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    @Lazy
    private PlayerEntity creator;

    @CreatedDate
    @CreationTimestamp
    private OffsetDateTime sta;

}