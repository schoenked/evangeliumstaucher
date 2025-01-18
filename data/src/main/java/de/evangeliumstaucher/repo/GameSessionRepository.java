package de.evangeliumstaucher.repo;

import de.evangeliumstaucher.entity.GameSessionEntity;
import de.evangeliumstaucher.entity.GameSessionStatus;
import de.evangeliumstaucher.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameSessionRepository extends JpaRepository<GameSessionEntity, Long> {
    @Query("""
            Select q 
            from
            GameSessionEntity gse
            join QuestionEntity q
                        on q.gameEntity.id = gse.game.id                        
            where gse.id = :id
                and q.id not in (SElECT question.id 
                                from
                                    UserQuestionEntity
                                where gameSession.id = gse.id
                                   and selectedVerse is not null )
            order by q.questionIndex asc
            limit 1
            """)
    Optional<QuestionEntity> findFirstBy(Long id);

    Optional<GameSessionEntity> findByPlayerIdAndGameId(Long playerId, UUID gameId);

    @Query(
            """
                        Select
                               CASE
                                   WHEN g IS NULL THEN 'NotStarted'
                                   WHEN COUNT(q) = 0 THEN 'Finished'
                                   ELSE 'InProgress'
                               END
                        from GameSessionEntity g
                        left join QuestionEntity q
                            on q.gameEntity.id = g.game.id
                                and q.id not in (SElECT question.id
                                    from
                                        UserQuestionEntity
                                    where gameSession.id = g.id
                                       and selectedVerse is not null )
                        where g.game.id = :quizId
                                            and g.player.id = :playerId
                                or g is null
                        GROUP BY g
                    """
    )
    GameSessionStatus getGameStatus(UUID quizId, Long playerId);

    /**
     * Returns all used bibles. Ordered by usage.
     */
    @Query("""
            Select g.bibleId
            from GameEntity g
            group by g.bibleId
            order by count(*) asc
            """)
    List<String> getBibleUsages();
}