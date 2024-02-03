package de.evangeliumstaucher.repo;

import de.evangeliumstaucher.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}