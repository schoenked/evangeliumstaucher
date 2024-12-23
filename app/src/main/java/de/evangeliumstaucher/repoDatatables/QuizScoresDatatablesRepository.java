package de.evangeliumstaucher.repoDatatables;

import de.evangeliumstaucher.entity.datatables.QuizScores;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizScoresDatatablesRepository extends DataTablesRepository<QuizScores, Long> {
}
