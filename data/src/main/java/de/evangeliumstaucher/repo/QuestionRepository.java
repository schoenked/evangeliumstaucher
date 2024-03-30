package de.evangeliumstaucher.repo;

import de.evangeliumstaucher.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {
    Optional<QuestionEntity> findByGameEntityIdAndQuestionIndex(UUID gameId, Long questionIndex);
}