package de.evangeliumstaucher.repo;

import de.evangeliumstaucher.entity.GameSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameSessionRepository extends JpaRepository<GameSessionEntity, Long> {
    Optional<GameSessionEntity> findByPlayerIdAndGameId(Long playerId, UUID gameId);
}