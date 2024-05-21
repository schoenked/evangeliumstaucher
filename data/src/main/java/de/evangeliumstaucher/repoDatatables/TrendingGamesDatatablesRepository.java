package de.evangeliumstaucher.repoDatatables;

import de.evangeliumstaucher.entity.datatables.GameRowTrend;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrendingGamesDatatablesRepository extends DataTablesRepository<GameRowTrend, UUID> {
}