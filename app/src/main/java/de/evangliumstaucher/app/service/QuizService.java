package de.evangliumstaucher.app.service;

import de.evangliumstaucher.app.model.BibleWrap;
import de.evangliumstaucher.app.model.BookWrap;
import de.evangliumstaucher.app.model.ChapterWrap;
import de.evangliumstaucher.app.model.VerseWrap;
import de.evangliumstaucher.app.utils.DontJudge;
import de.evangliumstaucher.app.utils.Fibonacci;
import de.evangliumstaucher.app.utils.ListUtils;
import de.evangliumstaucher.app.viewmodel.Part;
import de.evangliumstaucher.app.viewmodel.QuizModel;
import de.evangliumstaucher.app.viewmodel.RunningGame;
import de.evangliumstaucher.app.viewmodel.RunningQuestion;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Passage;
import de.evangeliumstaucher.repo.GameRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static de.evangliumstaucher.app.utils.DontJudge.getTimePoints;
import static org.springframework.web.client.HttpClientErrorException.BadRequest;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final BibleService bibleService;
    private final BookService bookService;
    private final ChaptersService chaptersService;
    private final VersesService versesService;
    private final PassageService passageService;
    private final GameRepository gameRepository;
    private final HashMap<String, QuizModel> quizzes = new HashMap<>();
    private final HashMap<String, RunningGame> userGameplays = new HashMap<>();

    @NotNull
    private static String getGampelayId(String userId, String quizId) {
        String gampelayId = quizId + userId;
        return gampelayId;
    }

    public QuizModel createQuiz(String bibleId) throws ApiException {
        QuizModel quizModel = null;
        BibleWrap bible = bookService.getBible(bibleId);

        quizModel = QuizModel.builder()
                .id(UUID.randomUUID().toString())
                .bible(bible)
                .build();
        quizzes.put(quizModel.getId(), quizModel);
        gameRepository.save(quizModel.getEntity());
        return quizModel;
    }

    private VerseWrap getRandomVerse(BibleWrap bible) throws ApiException {
        BookWrap book = ListUtils.randomItem(bible.getBooks());
        ChapterWrap chapter = ListUtils.randomItem(book.getChapters());
        List<VerseWrap> verses = chapter.getVerses(versesService);
        VerseWrap verse = ListUtils.randomItem(verses);
        return verse;
    }

    public VerseWrap getQuestionVerse(QuizModel quizModel, int questionId) throws BadRequest, ApiException {
        if (quizModel.getVerses().size() < questionId) {
            //wrong id
            throw HttpClientErrorException.create(HttpStatus.BAD_REQUEST, "Die Frage gibt es nicht", null, null, null);
        } else if (quizModel.getVerses().size() == questionId) {
            //generate new question
            VerseWrap newQuestion = getRandomVerse(quizModel.getBible());
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
                VerseWrap preVerse = q.getContextStartVerse().stepVerses(steps, versesService);
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
                VerseWrap postVerse = q.getContextEndVerse().stepVerses(steps, versesService);
                passageId = q.getContextEndVerse().getVerseSummary().getId()
                        + "-"
                        + postVerse.getVerseSummary().getId();
                q.setContextEndVerse(postVerse);

            }
        }

        return passageService.getPassage(q.getVerse().getVerseSummary().getBibleId(), passageId, null, false, false, false, false, false, null, false);
    }

    public RunningQuestion getQuestion(String userId, String quizId, Integer qId) throws ApiException {
        RunningQuestion runningQuestion;
        String gampelayId = getGampelayId(userId, quizId);
        if (!userGameplays.containsKey(gampelayId)) {
            userGameplays.put(gampelayId, new RunningGame().withQuizModel(quizzes.get(quizId)));
        }
        RunningGame gameplay = userGameplays.get(gampelayId);

        if (gameplay.getQuestions().size() - 1 >= qId) {
            //already started
            runningQuestion = gameplay.getQuestions().get(qId);
        } else {
            VerseWrap verse = getQuestionVerse(gameplay.getQuizModel(), qId);

            runningQuestion = gameplay.createRunningQuestion(versesService);
            runningQuestion.setUrl(gameplay.getQuizModel().getUrl() + qId + "/");
            runningQuestion.setVerse(verse);
            runningQuestion.setContextStartVerse(verse);
            runningQuestion.setContextEndVerse(verse);
        }
        return runningQuestion;
    }

    public Passage getPassage(String userId, String quizId, Integer qId, Part part) throws ApiException {
        return getPassage(userGameplays.get(getGampelayId(userId, quizId)).getQuestions().get(qId), part);
    }

    public int calcPoints(RunningQuestion runningQuestion) throws ApiException {
        int points = 100;

        long timePoints = getTimePoints(runningQuestion.getDuration());
        points -= timePoints;

        int diffPoints = getDiffPoints(runningQuestion);
        points -= diffPoints;
        return points;
    }

    private int getDiffPoints(RunningQuestion runningQuestion) throws ApiException {
        return DontJudge.getDiffPoints(runningQuestion.getDiffVerses(versesService));
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