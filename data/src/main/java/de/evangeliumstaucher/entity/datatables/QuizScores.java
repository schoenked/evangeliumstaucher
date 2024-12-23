package de.evangeliumstaucher.entity.datatables;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import java.util.Date;
import java.util.UUID;

@Entity
@Immutable
@FilterDef(name = "playerId",
        parameters = @ParamDef(name = "playerId", type = Long.class))
@FilterDef(name = "quizId",
        parameters = @ParamDef(name = "quizId", type = UUID.class))
@Subselect("""
                      SELECT
                          pe.username player,
                          (
                            SELECT
                                SUM(uqe.points)
                            FROM
                                user_question_entity uqe
                            WHERE
                                uqe.game_session_id = gse.id
                          ) points,
                          CAST(   ((  SELECT COUNT(*)
                               FROM user_question_entity uqe
                               WHERE uqe.game_session_id = gse.id
                                   AND uqe.answered_at IS NOT NULL)
                               / 
                               (   SELECT COUNT (*) 
                               FROM question_entity qe
                               WHERE qe.game_entity_id = g.id)
                                * 100) AS INT) || '%' 
                          progress,
                          gse.game_id quizId,
                          gse.started_at date,
                          pe.id playerId,
                          gse.id id
                      FROM
                          player_entity pe
                      JOIN
                          game_session_entity gse
                            ON gse.player_id = pe.id
                      ORDER BY (
                            SELECT
                                SUM(uqe.points)
                            FROM
                                user_question_entity uqe
                            WHERE
                                uqe.game_session_id = gse.id
                          ) DESC
        """)
//no player filter
@Filter(name = "playerId", condition = """
        (
            True
        )
        """)
@Filter(name = "quizId", condition = """
        (
            quizId = :quizId
        )
        """)
public class QuizScores {

    @Id
    @Column
    @JsonView
    private Long id;

    @Column
    @JsonView
    private String player;

    @Column(name = "date")
    @JsonView
    private Date date;

    @Column
    @JsonView
    private Integer progress;

    @Column
    @JsonView
    private Integer points;

}
