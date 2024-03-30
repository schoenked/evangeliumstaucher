package de.evangeliumstaucher.repo;

import de.evangeliumstaucher.entity.UserQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserQuestionRepository extends JpaRepository<UserQuestionEntity, UUID> {
    Optional<UserQuestionEntity> findByGameSessionIdAndQuestionId(Long gameSession, Long questionId);

    List<UserQuestionEntity> findByGameSessionId(Long gameSession);
}