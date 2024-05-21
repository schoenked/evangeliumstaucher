package de.evangeliumstaucher.entity.datatables;

import jakarta.persistence.Entity;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

@Entity
@Immutable
@Subselect(GameRow.SUBSELECT)
@FilterDef(name = "notAlreadyPlayedFilter",
        parameters = @ParamDef(name = "playerID", type = Long.class))
@Filter(name = "notAlreadyPlayedFilter", condition = """
        (
        SELECT COUNT(*)
        FROM game_session_entity gse
        WHERE
            gse.game_id = id
        AND
            gse.player_id = :playerID
        )
        = 0""")
public class GameRowTrend extends GameRow {

}
