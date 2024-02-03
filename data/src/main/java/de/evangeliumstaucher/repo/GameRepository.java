package de.evangeliumstaucher.repo;

import de.evangeliumstaucher.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}