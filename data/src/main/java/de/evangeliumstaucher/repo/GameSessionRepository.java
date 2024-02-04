package de.evangeliumstaucher.repo;

import de.evangeliumstaucher.entity.GameSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameSessionRepository extends JpaRepository<GameSessionEntity, Long> {
}