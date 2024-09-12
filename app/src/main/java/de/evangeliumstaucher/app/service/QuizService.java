package de.evangeliumstaucher.app.service;

import com.google.common.collect.Lists;
import de.evangeliumstaucher.app.model.BibleWrap;
import de.evangeliumstaucher.app.model.BookWrap;
import de.evangeliumstaucher.app.model.VerseWrap;
import de.evangeliumstaucher.app.utils.DontJudge;
import de.evangeliumstaucher.app.utils.Fibonacci;
import de.evangeliumstaucher.app.utils.ListUtils;
import de.evangeliumstaucher.app.viewmodel.*;
import de.evangeliumstaucher.entity.*;
import de.evangeliumstaucher.repo.GameRepository;
import de.evangeliumstaucher.repo.GameSessionRepository;
import de.evangeliumstaucher.repo.QuestionRepository;
import de.evangeliumstaucher.repo.UserQuestionRepository;
import de.evangeliumstaucher.repo.model.BibleBook;
import de.evangeliumstaucher.repo.model.Passage;
import de.evangeliumstaucher.repo.model.Verse;
import de.evangeliumstaucher.repo.service.Library;
import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.web.client.HttpClientErrorException.BadRequest;

@Service
@RequiredArgsConstructor
public class QuizService {
    public static final int COUNT_CONTEXT_EXTENSIONS = 6;
    private final Library library;
    private final GameRepository gameRepository;
    private final QuestionRepository questionRepository;
    @Getter
    private final UserQuestionRepository userQuestionRepository;
    private final GameSessionRepository gameSessionRepository;
    private final HashMap<String, RunningQuestion> runningQuestionHashMap = new HashMap<>();
    @Value("${hostname}")
    private String hostname;

    @Nonnull
    private static String getQuestionId(Long userId, UUID quizId, Long questionindex) {
        String questionId = String.join(".", quizId.toString(), userId.toString(), questionindex.toString());
        return questionId;
    }

    public String getShareUrl(QuizModel quizModel) {
        return hostname + quizModel.getUrl();
    }

    public QuizModel createQuiz(String bibleId, PlayerModel creator) {
        QuizModel quizModel = null;
        BibleWrap bible = new BibleWrap(bibleId, library.getBible(bibleId));

        quizModel = QuizModel.builder()
                .bible(bible)
                .creator(creator)
                .bibleId(bibleId)
                .build();
        quizModel.getVerses(this);
        GameEntity e = gameRepository.save(quizModel.getEntity());
        //apply generated uuid
        quizModel.setId(e.getId());
        LinkedList<QuestionEntity> questionEntities = Lists.newLinkedList();
        for (long i = 0; i < quizModel.getVerses(this).size(); i++) {
            Verse vers = quizModel.getVerses(this).get((int) i);
            QuestionEntity verseEntity = new QuestionEntity(null, e, i, vers.getId());
            questionEntities.add(verseEntity);
        }
        questionRepository.saveAll(questionEntities);

        return quizModel;
    }

    private Verse getRandomVerse(BibleWrap bible) {
        Verse verse = ListUtils.randomItem(bible.getBible().getVerses());
        return verse;
    }

    public Verse getQuestionVerse(QuizModel quizModel) throws BadRequest {
        return getRandomVerse(quizModel.getBible(library));
    }

    @Nullable
    public Passage getPassage(RunningQuestion q, Part part) {
        if (q.getSelectedVerse() != null) {
            //already selected
            return null;
        }
        String passageId = q.getVerse().getVerse().getId();

        switch (part) {
            case pre -> {
                int c = q.getExtendingPrePassageCount();
                if (c > COUNT_CONTEXT_EXTENSIONS) {
                    return null;
                }
                q.setExtendingPrePassageCount(c + 1);
                int steps = Fibonacci.nthFibonacciTerm(q.getExtendingPrePassageCount()) * -1;
                VerseWrap preVerse = q.getContextStartVerse().stepVerses(steps, library);
                passageId = preVerse.getVerse().getId()
                        + "-"
                        + q.getContextStartVerse().stepVerses(-1, library).getVerse().getId();
                q.setContextStartVerse(preVerse);
            }
            case post -> {
                int c = q.getExtendingPostPassageCount();
                if (c > COUNT_CONTEXT_EXTENSIONS) {
                    return null;
                }
                q.setExtendingPostPassageCount(c + 1);
                int steps = Fibonacci.nthFibonacciTerm(q.getExtendingPostPassageCount());
                VerseWrap postVerse = q.getContextEndVerse().stepVerses(steps, library);
                passageId = q.getContextEndVerse().stepVerses(1, library).getVerse().getId()
                        + "-"
                        + postVerse.getVerse().getId();
                q.setContextEndVerse(postVerse);
            }
        }

        return library.getPassage(q.getVerse().getVerse().getBibleId(), passageId);
    }

    public RunningQuestion getQuestion(Long userId, UUID quizId, Long qId) throws BadRequestException {
        Optional<GameSessionEntity> gamesessionoptional = gameSessionRepository.findByPlayerIdAndGameId(userId, quizId);
        GameSessionEntity gamesession;
        gamesession = gamesessionoptional.orElseGet(() -> createGameSession(userId, quizId));
        QuizModel quizModel = get(quizId);
        String questionId = getQuestionId(userId, quizId, qId);
        Optional<QuestionEntity> questionEntityWrap;
        QuestionEntity question;
        questionEntityWrap = questionRepository.findByGameEntityIdAndQuestionIndex(quizId, qId);
        if (!questionEntityWrap.isPresent()) {
            throw new BadRequestException("Ungültige Fragennummer");
        }
        question = questionEntityWrap.get();
        RunningQuestion q = new RunningQuestion(question, gamesession);
        q.setCountQuestions(quizModel.getVerses(this).size());
        q.setIndexQuestion(qId + 1);
        BibleWrap bible = quizModel.getBible(library);
        VerseWrap verse = VerseWrap.getVerse(question.getVerseId(), bible, library);

        q.setVerse(verse);
        List<? extends BibleBook> books = verse.getChapter().getBook()
                .getBible()
                .getBooks(library)
                .stream().map(BookWrap::getBook)
                .toList();
        q.setBooks(BookModel.from(books, "select", library));
        q.setUrl(quizModel.getUrl() + qId + "/");
        q.setVerse(verse);
        q.setContextStartVerse(verse);
        q.setContextEndVerse(verse);
        runningQuestionHashMap.put(questionId, q);

        Optional<UserQuestionEntity> userQestionEntityWrap = userQuestionRepository.findByGameSessionIdAndQuestionId(gamesession.getId(), q.getQuestionEntity().getId());

        if (userQestionEntityWrap.isEmpty()) {
            UserQuestionEntity e = new UserQuestionEntity();
            e.setQuestion(q.getQuestionEntity());
            e.setStartedAt(LocalDateTime.now());
            e.setGameSession(gamesession);
            e = userQuestionRepository.save(e);
        }

        return q;
    }

    private GameSessionEntity createGameSession(Long userId, UUID quizId) {
        GameSessionEntity gamesession = new GameSessionEntity();
        gamesession.setPlayer(new PlayerEntity().withId(userId));
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(quizId);
        gamesession.setGame(gameEntity);
        GameSessionEntity e = gameSessionRepository.save(gamesession);
        return e;
    }

    public QuizModel get(UUID quizId) {
        return QuizModel.from(gameRepository.findById(quizId).get(), library);
    }

    public Passage getPassage(Long userId, UUID quizId, Long qId, Part part) {
        return getPassage(runningQuestionHashMap.get(getQuestionId(userId, quizId, qId)), part);
    }

    public int calcPoints(RunningQuestion runningQuestion) {
        if (runningQuestion.getSelectedVerse() == null && runningQuestion.getAnsweredAt() == null) {
            // if not answered
            return 0;
        }
        int points = 100;

        long timePoints = DontJudge.getTimePointsSubtract(runningQuestion.getDuration());
        points -= timePoints;

        int diffPoints = DontJudge.getDiffPoints(runningQuestion.getDiffVerses(library));
        //limit 0-100
        points -= diffPoints;
        points = Math.min(points, 100);
        points = Math.max(points, 0);
        return points;
    }

    public int getSumPointsRunningGame(RunningQuestion runningQuestion) {
        int sum = userQuestionRepository.findByGameSessionId(runningQuestion.getGameSessionEntity().getId()).stream()
                .mapToInt(UserQuestionEntity::getPoints)
                .sum();
        return sum;
    }
}
