package de.evangeliumstaucher.entity.datatables;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

@Entity
@Immutable
@Subselect("""
        SELECT
            uqe.id id,
            pe.username player,
            uqe.points points,
            uqe.diff_verses diff_verses,
            EXTRACT(SECOND FROM(uqe.answered_at - uqe.started_at)) duration
        FROM
            user_question_entity uqe
        JOIN user_question_entity uqe2
            ON uqe.question_id = uqe2.question_id
        JOIN
            game_session_entity gse
            ON gse.id = uqe2.game_session_id
        JOIN player_entity pe
            ON pe.id = gse.player_id
        ORDER BY uqe.points DESC
        """)
public class QuestionScores {

    @Id
    @JsonView
    @Column
    Long id;

    @Column
    @JsonView
    private Integer duration;

    @Column
    @JsonView
    private String player;

    @Column(name = "diff_verses")
    @JsonView
    private Integer diffVerses;

    @Column
    @JsonView
    private Integer points;
}
