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

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Immutable
@Subselect("""
        SELECT
            g.id id,
            g.name name,
            gse.started_at started_at,
            gse.player_id player_id,
            '/quiz/' || g.id || '/' location,
            CAST(   ((  SELECT COUNT(*)
                FROM user_question_entity uqe
                WHERE uqe.game_session_id = gse.id
                    AND uqe.answered_at IS NOT NULL) * 100
                / 
                (   SELECT COUNT (*) 
                FROM question_entity qe
                WHERE qe.game_entity_id = g.id)) AS INT) || '%' progress
        FROM 
            game_entity g
        JOIN game_session_entity gse
            ON gse.game_id = g.id
        """)
@FilterDef(name = "startedDivesPlayedFilter",
        parameters = @ParamDef(name = "playerID", type = Long.class))
@Filter(name = "startedDivesPlayedFilter", condition = """
        (
        player_id = :playerID
        )
        """)
public class GameRowMyDives {

    @Id
    @JsonView
    UUID id;
    @Column
    @JsonView
    String name;
    @Column(name = "location")
    @JsonView
    String location;
    @Column(name = "started_at")
    @JsonView
    private OffsetDateTime startedAt;
    @JsonView
    private String progress;

}
