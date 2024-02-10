package de.evangeliumstaucher.app.service;

import de.evangeliumstaucher.app.model.BibleWrap;
import de.evangeliumstaucher.app.model.BookWrap;
import de.evangeliumstaucher.app.model.ChapterWrap;
import de.evangeliumstaucher.app.model.VerseWrap;
import de.evangeliumstaucher.app.utils.DontJudge;
import de.evangeliumstaucher.app.utils.Fibonacci;
import de.evangeliumstaucher.app.utils.ListUtils;
import de.evangeliumstaucher.app.viewmodel.Part;
import de.evangeliumstaucher.app.viewmodel.QuizModel;
import de.evangeliumstaucher.app.viewmodel.RunningGame;
import de.evangeliumstaucher.app.viewmodel.RunningQuestion;
import de.evangeliumstaucher.entity.GameEntity;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Passage;
import de.evangeliumstaucher.repo.GameRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.springframework.web.client.HttpClientErrorException.BadRequest;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final ApiServices apiServices;
    private final GameRepository gameRepository;
    private final HashMap<String, RunningGame> userGameplays = new HashMap<>();

    @NotNull
    private static String getGampelayId(String userId, String quizId) {
        String gampelayId = quizId + userId;
        return gampelayId;
    }

    public QuizModel createQuiz(String bibleId, String creator) throws ApiException {
        QuizModel quizModel = null;
        BibleWrap bible = apiServices.getBookService().getBible(bibleId);

        quizModel = QuizModel.builder()
                .bible(bible)
                .creator(creator)
                .bibleId(bibleId)
                .build();
        GameEntity e = gameRepository.save(quizModel.getEntity());
        //aply generated uuid
        quizModel.setId(e.getId());
        return quizModel;
    }

    private VerseWrap getRandomVerse(BibleWrap bible) throws ApiException {
        BookWrap book = ListUtils.randomItem(bible.getBooks(apiServices.getBookService()));
        ChapterWrap chapter = ListUtils.randomItem(book.getChapters());
        List<VerseWrap> verses = chapter.getVerses(apiServices.getVersesService());
        VerseWrap verse = ListUtils.randomItem(verses);
        return verse;
    }

    public VerseWrap getQuestionVerse(QuizModel quizModel, int questionId) throws BadRequest, ApiException {
        if (quizModel.getVerses().size() < questionId) {
            //wrong id
            throw HttpClientErrorException.create(HttpStatus.BAD_REQUEST, "Die Frage gibt es nicht", null, null, null);
        } else if (quizModel.getVerses().size() == questionId) {
            //generate new question
            VerseWrap newQuestion = getRandomVerse(quizModel.getBible(apiServices.getBibleService()));
            quizModel.getVerses().add(newQuestion);
        }
        return quizModel.getVerses().get(questionId);
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
                if (c > 4) {
                    return null;
                }
                q.setExtendingPrePassageCount(c + 1);
                int steps = Fibonacci.nthFibonacciTerm(q.getExtendingPrePassageCount()) * -1;
                VerseWrap preVerse = q.getContextStartVerse().stepVerses(steps, apiServices);
                passageId = preVerse.getVerseSummary().getId()
                        + "-"
                        + q.getContextStartVerse().getVerseSummary().getId();
                q.setContextStartVerse(preVerse);
            }
            case post -> {
                int c = q.getExtendingPostPassageCount();
                if (c > 4) {
                    return null;
                }
                q.setExtendingPostPassageCount(c + 1);
                int steps = Fibonacci.nthFibonacciTerm(q.getExtendingPostPassageCount());
                VerseWrap postVerse = q.getContextEndVerse().stepVerses(steps, apiServices);
                passageId = q.getContextEndVerse().getVerseSummary().getId()
                        + "-"
                        + postVerse.getVerseSummary().getId();
                q.setContextEndVerse(postVerse);

            }
        }

        return apiServices.getPassageService().getPassage(q.getVerse().getVerseSummary().getBibleId(), passageId, null, false, false, false, false, false, null, false);
    }

    public RunningQuestion getQuestion(String userId, UUID quizId, Integer qId) throws ApiException {
        RunningQuestion runningQuestion;
        String gampelayId = getGampelayId(userId, quizId.toString());
        if (!userGameplays.containsKey(gampelayId)) {
            GameEntity e = get(quizId);
            userGameplays.put(gampelayId, new RunningGame()
                    .withQuizModel(QuizModel.from(e, apiServices.getBibleService())));
        }
        RunningGame gameplay = userGameplays.get(gampelayId);

        if (gameplay.getQuestions().size() - 1 >= qId) {
            //already started
            runningQuestion = gameplay.getQuestions().get(qId);
        } else {
            VerseWrap verse = getQuestionVerse(gameplay.getQuizModel(), qId);

            runningQuestion = gameplay.createRunningQuestion(apiServices);
            runningQuestion.setUrl(gameplay.getQuizModel().getUrl() + qId + "/");
            runningQuestion.setVerse(verse);
            runningQuestion.setContextStartVerse(verse);
            runningQuestion.setContextEndVerse(verse);
        }
        return runningQuestion;
    }

    private GameEntity get(UUID quizId) {
        return gameRepository.findById(quizId).get();
    }

    public Passage getPassage(String userId, String quizId, Integer qId, Part part) throws ApiException {
        return getPassage(userGameplays.get(getGampelayId(userId, quizId)).getQuestions().get(qId), part);
    }

    public int calcPoints(RunningQuestion runningQuestion) throws ApiException {
        int points = 100;

        long timePoints = DontJudge.getTimePoints(runningQuestion.getDuration());
        points -= timePoints;

        int diffPoints = getDiffPoints(runningQuestion);
        points -= diffPoints;
        return points;
    }

    private int getDiffPoints(RunningQuestion runningQuestion) throws ApiException {
        return DontJudge.getDiffPoints(runningQuestion.getDiffVerses(apiServices));
    }


    public int getSumPointsRunningGame(RunningQuestion runningQuestion) {
        int sum = runningQuestion.getRunningGame().getQuestions().stream()
                .mapToInt(q -> {
                    try {
                        return q.getPoints(this);
                    } catch (ApiException e) {
                        throw new RuntimeException(e);
                    }
                })
                .sum();
        return sum;
    }
}
