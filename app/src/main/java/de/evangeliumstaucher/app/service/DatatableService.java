package de.evangeliumstaucher.app.service;

import de.evangeliumstaucher.entity.datatables.*;
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

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        DataTablesOutput<GameRowTrend> all = trendingGamesDatatablesRepository.findAll(input);

        //set position
        List<GameRow> copy = all.getData().stream()
                .sorted(Comparator.comparing(GameRow::getRanking).reversed())
                .collect(Collectors.toList());

        for (int i = 0; i < copy.size(); i++) {
            copy.get(i).setPosition(i + 1);
        }

        return all;
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
