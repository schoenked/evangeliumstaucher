package de.evangeliumstaucher.repo;

import de.evangeliumstaucher.entity.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
}