package de.evangeliumstaucher.app.service;

import de.evangeliumstaucher.entity.datatables.GameRowMyDives;
import de.evangeliumstaucher.entity.datatables.GameRowTrend;
import de.evangeliumstaucher.entity.datatables.QuestionScores;
import de.evangeliumstaucher.entity.datatables.QuizScores;
import de.evangeliumstaucher.repoDatatables.MyGamesDatatablesRepository;
import de.evangeliumstaucher.repoDatatables.QuestionScoresDatatablesRepository;
import de.evangeliumstaucher.repoDatatables.QuizScoresDatatablesRepository;
import de.evangeliumstaucher.repoDatatables.TrendingGamesDatatablesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DatatableService {
    private final TrendingGamesDatatablesRepository trendingGamesDatatablesRepository;
    private final MyGamesDatatablesRepository myGamesDatatablesRepository;
    private final QuestionScoresDatatablesRepository questionScoresDatatablesRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private final QuizScoresDatatablesRepository quizScoresDatatablesRepluginspository;

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

    public DataTablesOutput<QuestionScores> getQuestoinScores(DataTablesInput input, Long questionId, Long playerId) {
        Session session = entityManager.unwrap(Session.class);
        Filter f = session.enableFilter("userquestionentityid");
        f.setParameter("userquestionentityid", questionId);
        return questionScoresDatatablesRepository.findAll(input);
    }

    public DataTablesOutput<QuizScores> getQuizScores(@Valid DataTablesInput input, @NotNull UUID quizid, Long playerId) {
        Session session = entityManager.unwrap(Session.class);
        Filter f_playerID = session.enableFilter("playerId");
        f_playerID.setParameter("playerId", playerId);
        Filter f_quizId = session.enableFilter("quizId");
        f_quizId.setParameter("quizId", quizid);
        return quizScoresDatatablesRepluginspository.findAll(input);
    }
}
