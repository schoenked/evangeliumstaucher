package de.evangeliumstaucher.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestionEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;
    @ManyToOne
    @JoinColumn(name = "gameSession_id")
    private GameSessionEntity gameSession;

    private Integer points;
    private LocalDateTime startedAt;
    private LocalDateTime answeredAt;
    private String selectedVerse;
    private Integer diffVerses;

    public Duration getDuration() {
        return Duration.between(getStartedAt(), getAnsweredAt());
    }

}