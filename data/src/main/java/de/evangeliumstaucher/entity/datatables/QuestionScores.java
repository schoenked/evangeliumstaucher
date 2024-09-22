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

@Entity
@Immutable
@FilterDef(name = "userquestionentityid",
        parameters = @ParamDef(name = "userquestionentityid", type = Long.class))
@Subselect("""
                      SELECT
                          uqe2.id id,
                          pe.username player,
                          uqe2.points points,
                          uqe2.diff_verses diff_verses,
                          EXTRACT(EPOCH FROM (uqe2.answered_at - uqe2.started_at)) duration,
                           (select SUM(points) from (SELECT uqe3.points
                                FROM user_question_entity uqe3
                                INNER JOIN
                                    game_session_entity gse2
                                ON gse2.id = uqe3.game_session_id
                                    AND gse2.game_id = gse.game_id
                                INNER JOIN
                                    player_entity pe2
                                ON pe2.id = gse2.player_id
                                    AND pe2.id = pe.id
                                WHERE
                                    uqe3.points > 0) points) as gesamt,
                          uqe.id userquestionentityid
                      FROM
                          user_question_entity uqe
                      JOIN user_question_entity uqe2
                          ON uqe.question_id = uqe2.question_id
                      JOIN
                          game_session_entity gse
                          ON gse.id = uqe2.game_session_id
                      JOIN player_entity pe
                          ON pe.id = gse.player_id
                      ORDER BY uqe2.points DESC
        """)
@Filter(name = "userquestionentityid", condition = """
        (
            userquestionentityid = :userquestionentityid
        )
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

    @Column
    @JsonView
    private Integer gesamt;

}
