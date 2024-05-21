package de.evangeliumstaucher.app.service;

import de.evangeliumstaucher.entity.datatables.GameRowMyDives;
import de.evangeliumstaucher.entity.datatables.GameRowTrend;
import de.evangeliumstaucher.repoDatatables.MyGamesDatatablesRepository;
import de.evangeliumstaucher.repoDatatables.TrendingGamesDatatablesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatatableService {
    private final TrendingGamesDatatablesRepository trendingGamesDatatablesRepository;
    private final MyGamesDatatablesRepository myGamesDatatablesRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public DataTablesOutput<GameRowTrend> getTrendingGames(DataTablesInput input, Long playerId) {
        Session session = entityManager.unwrap(Session.class);
        Filter f = session.enableFilter("notAlreadyPlayedFilter");
        f.setParameter("playerID", playerId);
        return trendingGamesDatatablesRepository.findAll(input);
    }

    public DataTablesOutput<GameRowMyDives> getMyDives(DataTablesInput input, Long playerId) {
        Session session = entityManager.unwrap(Session.class);
        Filter f = session.enableFilter("startedDivesPlayedFilter");
        f.setParameter("playerID", playerId);
        return myGamesDatatablesRepository.findAll(input);
    }
}
