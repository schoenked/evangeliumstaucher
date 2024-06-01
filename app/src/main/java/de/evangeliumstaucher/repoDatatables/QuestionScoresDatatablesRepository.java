package de.evangeliumstaucher.repoDatatables;

import de.evangeliumstaucher.entity.datatables.QuestionScores;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionScoresDatatablesRepository extends DataTablesRepository<QuestionScores, Long> {
}
