package de.evangeliumstaucher.app.service;

import com.google.common.collect.Lists;
import de.evangeliumstaucher.app.model.BibleWrap;
import de.evangeliumstaucher.app.model.BookWrap;
import de.evangeliumstaucher.app.model.ChapterWrap;
import de.evangeliumstaucher.app.model.VerseWrap;
import de.evangeliumstaucher.app.utils.DontJudge;
import de.evangeliumstaucher.app.utils.Fibonacci;
import de.evangeliumstaucher.app.utils.ListUtils;
import de.evangeliumstaucher.app.viewmodel.Part;
import de.evangeliumstaucher.app.viewmodel.PlayerModel;
import de.evangeliumstaucher.app.viewmodel.QuizModel;
import de.evangeliumstaucher.app.viewmodel.RunningQuestion;
import de.evangeliumstaucher.entity.*;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Passage;
import de.evangeliumstaucher.repo.GameRepository;
import de.evangeliumstaucher.repo.GameSessionRepository;
import de.evangeliumstaucher.repo.QuestionRepository;
import de.evangeliumstaucher.repo.UserQuestionRepository;
import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.web.client.HttpClientErrorException.BadRequest;

@Service
@RequiredArgsConstructor
public class QuizService {
    public static final int COUNT_CONTEXT_EXTENSIONS = 4;
    private final ApiServices apiServices;
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

    public QuizModel createQuiz(String bibleId, PlayerModel creator) throws ApiException {
        QuizModel quizModel = null;
        BibleWrap bible = apiServices.getBookService().getBible(bibleId);

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
            VerseWrap vers = quizModel.getVerses(this).get((int) i);
            QuestionEntity verseEntity = new QuestionEntity(null, e, i, vers.getVerseSummary().getId());
            questionEntities.add(verseEntity);
        }
        questionRepository.saveAll(questionEntities);

        return quizModel;
    }

    private VerseWrap getRandomVerse(BibleWrap bible) throws ApiException {
        BookWrap book = ListUtils.randomItem(bible.getBooks(apiServices.getBookService()));
        ChapterWrap chapter = ListUtils.randomItem(book.getChapters());
        List<VerseWrap> verses = chapter.getVerses(apiServices.getVersesService());
        VerseWrap verse = ListUtils.randomItem(verses);
        return verse;
    }

    public VerseWrap getQuestionVerse(QuizModel quizModel) throws BadRequest, ApiException {
        return getRandomVerse(quizModel.getBible(apiServices.getBibleService()));
    }

    @Nullable
    public Passage getPassage(RunningQuestion q, Part part) throws ApiException {
        if (q.getSelectedVerse() != null) {
            //already selected
            return null;
        }
        String passageId = q.getVerse().getVerseSummary().getId();

        switch (part) {
            case pre -> {
                int c = q.getExtendingPrePassageCount();
                if (c > COUNT_CONTEXT_EXTENSIONS) {
                    return null;
                }
                q.setExtendingPrePassageCount(c + 1);
                int steps = Fibonacci.nthFibonacciTerm(q.getExtendingPrePassageCount()) * -1;
                VerseWrap preVerse = q.getContextStartVerse().stepVerses(steps, apiServices);
                passageId = preVerse.getVerseSummary().getId()
                        + "-"
                        + q.getContextStartVerse().stepVerses(-1, apiServices).getVerseSummary().getId();
                q.setContextStartVerse(preVerse);
            }
            case post -> {
                int c = q.getExtendingPostPassageCount();
                if (c > COUNT_CONTEXT_EXTENSIONS) {
                    return null;
                }
                q.setExtendingPostPassageCount(c + 1);
                int steps = Fibonacci.nthFibonacciTerm(q.getExtendingPostPassageCount());
                VerseWrap postVerse = q.getContextEndVerse().stepVerses(steps, apiServices);
                passageId = q.getContextEndVerse().stepVerses(1, apiServices).getVerseSummary().getId()
                        + "-"
                        + postVerse.getVerseSummary().getId();
                q.setContextEndVerse(postVerse);
            }
        }

        return apiServices.getPassageService().getPassage(q.getVerse().getVerseSummary().getBibleId(), passageId, null, false, false, false, false, false, null, false);
    }

    public RunningQuestion getQuestion(Long userId, UUID quizId, Long qId) throws ApiException, BadRequestException {
        RunningQuestion runningQuestion;
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
        BibleWrap bible = quizModel.getBible(apiServices.getBibleService());
        VerseWrap verse = RunningQuestion.getVerse(question.getVerseId(), bible, apiServices);
        q.setVerse(verse);
        q.setUrl(quizModel.getUrl() + qId + "/");
        q.setVerse(verse);
        q.setContextStartVerse(verse);
        q.setContextEndVerse(verse);
        runningQuestionHashMap.put(questionId, q);

        Optional<UserQuestionEntity> userQestionEntityWrap = userQuestionRepository.findByGameSessionIdAndQuestionId(gamesession.getId(), q.getQuestionEntity().getId());

        if (userQestionEntityWrap.isEmpty()) {
            UserQuestionEntity e = new UserQuestionEntity();
            e.setQuestion(q.getQuestionEntity());
            e.setStartedAt(q.getStartedAt());
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
        return QuizModel.from(gameRepository.findById(quizId).get(), apiServices.getBibleService());
    }

    public Passage getPassage(Long userId, UUID quizId, Long qId, Part part) throws ApiException {
        return getPassage(runningQuestionHashMap.get(getQuestionId(userId, quizId, qId)), part);
    }

    public int calcPoints(RunningQuestion runningQuestion) throws ApiException {
        if (runningQuestion.getSelectedVerse() == null && runningQuestion.getAnsweredAt() == null) {
            // if not answered
            return 0;
        }
        int points = 100;

        long timePoints = DontJudge.getTimePointsSubtract(runningQuestion.getDuration());
        points -= timePoints;

        int diffPoints = DontJudge.getDiffPoints(runningQuestion.getDiffVerses(apiServices));
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
