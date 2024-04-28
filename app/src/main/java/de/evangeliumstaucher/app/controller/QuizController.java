package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.service.ApiServices;
import de.evangeliumstaucher.app.service.QuizService;
import de.evangeliumstaucher.app.service.UserService;
import de.evangeliumstaucher.app.viewmodel.*;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Passage;
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

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@Slf4j
public class QuizController extends BaseController {
    private final QuizService quizService;
    private final UserService userService;
    private final HttpSession session;

    public QuizController(ApiServices apiServices, QuizService quizService, UserService userService, HttpSession session) {
        super(apiServices);
        this.quizService = quizService;
        this.userService = userService;
        this.session = session;
    }

    @ModelAttribute("playerModel")
    public PlayerModel PlayerModelAttribute(@AuthenticationPrincipal OAuth2User oidcUser) {
        return PlayerModel.from(userService.getByEMail(oidcUser.getAttribute("email")).get());
    }

    /**
     * Creates new Quiz for the given bible
     *
     * @param bibleId
     * @param m
     * @return
     */
    @GetMapping("/quiz/create/{bibleId}/")
    public RedirectView getQuiz(@PathVariable String bibleId, Model m, @ModelAttribute PlayerModel playerModel) {
        try {
            QuizModel quizModel = quizService.createQuiz(bibleId, playerModel);
            return new RedirectView(quizModel.getUrl());
        } catch (ApiException e) {
            log.error("failed", e);
            addWarning(m);
        }
        return null;
    }

    @GetMapping("/quiz/{quizId}/")
    public String getQuestion(@PathVariable UUID quizId, Model m) {
        QuizModel quiz = quizService.get(quizId);
        m.addAttribute("quiz", quiz);
        m.addAttribute("quizService", quizService);
        return "quiz-info.html";
    }

    @GetMapping("/quiz/{quizId}/{qId}/")
    public String getQuestion(@PathVariable UUID quizId, @PathVariable Long qId, @ModelAttribute PlayerModel playerModel, Model m) throws BadRequestException {
        try {
            RunningQuestion runningQuestion = quizService.getQuestion(playerModel.getId(), quizId, qId);
            m.addAttribute("question", runningQuestion);
            return "quiz.html";
        } catch (ApiException e) {
            log.error("failed", e);
            addWarning(m);
        }
        return null;
    }

    @GetMapping("/quiz/{quizId}/{qId}/{part}")
    public String getQuizPost(@PathVariable UUID quizId, @PathVariable Long qId, @PathVariable Part part, @ModelAttribute PlayerModel playerModel, Model m) {
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
        } catch (ApiException e) {
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
    public String selectVerseGetResult(@PathVariable UUID quizId, @PathVariable Long qId, @PathVariable String verseId, @ModelAttribute PlayerModel playerModel, Model m) throws BadRequestException {
        try {
            log.debug("selectVerseGetResult() called with: quizId = [" + quizId + "], qId = [" + qId + "], verseId = [" + verseId + "], m = [" + m + "]");

            RunningQuestion runningQuestion = quizService.getQuestion(playerModel.getId(), quizId, qId);
            runningQuestion.setAnsweredAt(LocalDateTime.now());
            runningQuestion.setSelectedVerse(verseId, apiServices);
            runningQuestion.updateEntity(quizService);

            ResultModel resultModel = new ResultModel();
            resultModel.setVerseDiff(runningQuestion.getDiffVerses(apiServices));
            resultModel.setTimespan(runningQuestion.getTimespan());
            resultModel.setPoints(runningQuestion.getPoints(quizService));
            resultModel.setPointsSum(quizService.getSumPointsRunningGame(runningQuestion));
            resultModel.setSelectedVerse(runningQuestion.getSelectedVerse().getText());
            resultModel.setSearchedVerse(runningQuestion.getVerse().getText());
            resultModel.setUrlNext(runningQuestion.getQuizModel(apiServices).getUrl() + (qId + 1) + "/");
            m.addAttribute("model", resultModel);
        } catch (ApiException e) {
            log.error("failed", e);
            addWarning(m);
        }
        return "result.html";
    }

    @GetMapping("/quiz/create/")
    public String getQuiz(Model m) {
        return super.getBible(m);
    }
}
