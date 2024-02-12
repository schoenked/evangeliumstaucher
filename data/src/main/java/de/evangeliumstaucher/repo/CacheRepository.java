package de.evangeliumstaucher.repo;

import de.evangeliumstaucher.entity.CacheEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CacheRepository extends JpaRepository<CacheEntity, Long> {
    CacheEntity findFirstByKeyOrderByExpiryDesc(String key);
}