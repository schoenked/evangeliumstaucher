package de.evangeliumstaucher.repo;

import de.evangeliumstaucher.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    Optional<PlayerEntity> findByUsername(String name);

    boolean existsPlayerEntityByUsername(String name);

    Optional<PlayerEntity> findByEmail(String email);

}