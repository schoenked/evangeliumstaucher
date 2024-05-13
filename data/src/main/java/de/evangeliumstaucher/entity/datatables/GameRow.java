package de.evangeliumstaucher.entity.datatables;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
            pe.username creator,
            g.created_at created_at,
            (   SELECT COUNT(*)
                FROM game_session_entity gse
                WHERE  gse.game_id = g.id) player_count
        FROM game_entity g
        JOIN player_entity pe 
            ON pe.id = g.creator_id
        """)
public class GameRow {
    @Id
    @JsonView
    UUID id;

    @Column
    @JsonView
    Integer name;

    @Column(name = "player_count")
    @JsonView
    Integer playerCount;

    @Column
    @JsonView
    String creator;

    @Column(name = "created_at")
    @JsonView
    OffsetDateTime createdAt;
}
