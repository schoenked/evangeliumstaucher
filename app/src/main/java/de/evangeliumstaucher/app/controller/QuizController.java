package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.service.QuizService;
import de.evangeliumstaucher.app.service.UserService;
import de.evangeliumstaucher.app.viewmodel.*;
import de.evangeliumstaucher.repo.model.Passage;
import de.evangeliumstaucher.repo.service.Library;
import de.evangeliumstaucher.repoDatatables.TrendingGamesDatatablesRepository;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import javax.naming.ServiceUnavailableException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class QuizController extends BaseController {
    private final QuizService quizService;
    private final UserService userService;
    private final HttpSession session;
    private final TrendingGamesDatatablesRepository gameRepository;

    public QuizController(Library library, QuizService quizService, UserService userService, HttpSession session, TrendingGamesDatatablesRepository gameRepository) {
        super(library);
        this.quizService = quizService;
        this.userService = userService;
        this.session = session;
        this.gameRepository = gameRepository;
    }

    private static void handleUnavailable() throws ServiceUnavailableException {
        throw new ServiceUnavailableException("Service vorübergehend nicht verfügbar");
    }

    @ModelAttribute("playerModel")
    public PlayerModel PlayerModelAttribute(@AuthenticationPrincipal OAuth2User oidcUser) {
        return PlayerModel.from(userService.getByEMail(oidcUser.getAttribute("email")).get());
    }

    @GetMapping("/quiz/")
    public RedirectView getQuiz(Model m) {
        return new RedirectView("/quiz/pier/");
    }

    @GetMapping("/quiz/{quizId}/")
    public String getInfo(@PathVariable UUID quizId, Model m) {
        QuizModel quiz = quizService.get(quizId);
        m.addAttribute("quiz", quiz);
        m.addAttribute("quizService", quizService);
        DatatableViewModel scoreTableModel = new DatatableViewModel();
        List<DatatableColumn> columns = List.of(
                new DatatableColumn("Punkte", "points", "num").withOrder(DatatableColumn.Order.DESC),
                new DatatableColumn("Spieler", "player"),
                new DatatableColumn("Fortschritt", "progress", "num-fmt"),
                new DatatableColumn("am", "date", "date")
        );
        scoreTableModel.setColumns(columns);
        scoreTableModel.setAutoReloader(true);
        scoreTableModel.setUrl("/quiz/datatable/quiz-scores/" + quizId);
        m.addAttribute("scoreTable", scoreTableModel);

        return "quiz-info.html";
    }

    @GetMapping("/quiz/{quizId}/next")
    public String getQuestion(@PathVariable UUID quizId, @ModelAttribute PlayerModel playerModel, Model m) throws BadRequestException, ServiceUnavailableException {
        try {
            RunningQuestion runningQuestion = quizService.getQuestion(playerModel.getId(), quizId, null);
            if (runningQuestion == null) {
                return "redirect:.";
            }
            m.addAttribute("question", runningQuestion);
            return "quiz.html";
        } catch (Exception e) {
            log.error("failed", e);
            addWarning(m);
            handleUnavailable();
        }
        return null;
    }

    @GetMapping("/quiz/{quizId}/{next}/{qId}/{part}")
    public String getQuizPost(@PathVariable UUID quizId, @PathVariable Integer qId, @PathVariable Part part, @ModelAttribute PlayerModel playerModel, Model m) {
        try {
            Passage passage = quizService.getPassage(playerModel.getId(), quizId, qId, part);
            PassageModel model = PassageModel.from(passage);
            model.setPath(part.name());
            if (part == Part.origin) {
                model.setHtmlClasses("border-secondary border-2 my-4");
            } else if (part == Part.pre) {
                model.setHtmlClasses("animate__animated animate__fadeInDown");
            } else {
                model.setHtmlClasses("animate__animated animate__fadeInUp");
            }
            if (part != Part.origin && model.getContent() != null) {
                PassageModel.PassageLoader loader = new PassageModel.PassageLoader();
                model.setPassageLoader(loader);
            }
            if (passage != null && passage.getId() != null) {
                model.setId(passage.getId() + "_passage");
            }
            m.addAttribute("model", model);
        } catch (Exception e) {
            log.error("failed", e);
            addWarning(m);

            PassageModel model = new PassageModel();
            model.setContent("");

            model.setPath(part.name());
            if (part == Part.origin) {
                model.setHtmlClasses("border-secondary border-2 my-4");
            }
            if (part != Part.origin && model.getContent() != null) {
                PassageModel.PassageLoader loader = new PassageModel.PassageLoader();
                model.setPassageLoader(loader);
            }
            m.addAttribute("model", model);
        }
        return "passageContextLoading.html";
    }

    @GetMapping("/quiz/{quizId}/{qId}/select/{verseId}")
    public String selectVerseGetResult(@PathVariable UUID quizId, @PathVariable Integer qId, @PathVariable String verseId, @ModelAttribute PlayerModel playerModel, Model m) throws BadRequestException, ServiceUnavailableException {
        try {
            log.debug("selectVerseGetResult() called with: quizId = [" + quizId + "], qId = [" + qId + "], verseId = [" + verseId + "], m = [" + m + "]");

            RunningQuestion runningQuestion = quizService.getQuestion(playerModel.getId(), quizId, qId);
            runningQuestion.setAnsweredAt(LocalDateTime.now());
            runningQuestion.setSelectedVerse(verseId, library);
            Long id = runningQuestion.syncEntity(quizService, library);

            ResultModel resultModel = new ResultModel();
            resultModel.setIndexQuestion(runningQuestion.getIndexQuestion() + 1);
            resultModel.setCountQuestions(runningQuestion.getCountQuestions());
            resultModel.setVerseDiff(runningQuestion.getDiffVerses(library));
            resultModel.setTimespan(runningQuestion.getTimespan());
            resultModel.setPoints(runningQuestion.getPoints(quizService));
            resultModel.setPointsSum(quizService.getSumPointsRunningGame(runningQuestion));
            resultModel.setSelectedVerse(runningQuestion.getSelectedVerse().getTeXTLong());
            resultModel.setSearchedVerse(runningQuestion.getVerse().getTeXTLong());
            QuizModel quizModel = runningQuestion.getQuizModel(library);
            resultModel.setUrlNext(runningQuestion.getUrl());
            m.addAttribute("model", resultModel);
            m.addAttribute("quiz", quizModel);
            m.addAttribute("quizService", quizService);

            DatatableViewModel questionScoreTable = getDatatableQuestionScore(id);
            m.addAttribute("questionScoreTable", questionScoreTable);
        } catch (Exception e) {
            log.error("failed", e);
            addWarning(m);
            handleUnavailable();
        }
        return "result.html";
    }

    private @Nonnull DatatableViewModel getDatatableQuestionScore(Long id) {
        DatatableViewModel questionScoreTable = new DatatableViewModel();
        List<DatatableColumn> columns = List.of(
                new DatatableColumn("Spieler", "player"),
                new DatatableColumn("Punkte", "points", "num").withOrder(DatatableColumn.Order.DESC),
                new DatatableColumn("Distanz", "diffVerses", "num"),
                new DatatableColumn("Dauer", "duration", "num"),
                new DatatableColumn("Gesamt", "gesamt", "num")
        );
        questionScoreTable.setColumns(columns);
        questionScoreTable.setUrl("/quiz/datatable/questionscore/" + id);
        questionScoreTable.setAutoReloader(true);
        return questionScoreTable;
    }

}
