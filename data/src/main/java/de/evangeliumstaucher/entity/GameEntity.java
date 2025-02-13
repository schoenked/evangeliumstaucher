package de.evangeliumstaucher.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

//todo check entity indexes

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

    //todo set varchar columns length
    @Column(unique = true, length = 500) // Set the name field to be unique
    @NotNull
    private String name;

    @Column(length = 2000)
    private String description;

    @NotNull
    private String bibleId;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    @Lazy
    private PlayerEntity creator;

    @CreatedDate
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "game_tags", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "tag")
    private List<String> tags;
    @NotNull
    private int distanceRatingFactor;
    @NotNull
    private int timeRatingFactor;
    @Column(length = 2000)
    private String whitelist;
    @Column(length = 2000)
    private String blacklist;
}