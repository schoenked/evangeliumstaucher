package com.devrezaur.main.controller;

import com.devrezaur.main.service.*;
import com.devrezaur.main.viewmodel.PassageModel;
import com.devrezaur.main.viewmodel.QuizModel;
import com.devrezaur.main.viewmodel.ResultModel;
import com.devrezaur.main.viewmodel.RunningQuestion;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Passage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@Slf4j
public class QuizController extends BaseController {
    private final QuizService quizService;
    private final HttpSession session;

    public QuizController(BibleService bibleService, BookService bookService, ChaptersService chaptersService, VersesService versesService, PassageService passageService, QuizService quizService, HttpSession session) {
        super(bibleService, bookService, chaptersService, versesService, passageService);
        this.quizService = quizService;
        this.session = session;
    }

    private String getUserId() {
        return session.getId();
    }

    /**
     * Creates new Quiz for the given bible
     *
     * @param bibleId
     * @param m
     * @return
     */
    @GetMapping("/quiz/create/{bibleId}/")
    public RedirectView getQuiz(@PathVariable String bibleId, Model m) {
        try {
            QuizModel quizModel = quizService.createQuiz(bibleId);
            return new RedirectView("/quiz/" + quizModel.getId() + "/0/");
        } catch (ApiException e) {
            log.error("failed", e);
            addWarning(m);
        }
        return null;
    }

    @GetMapping("/quiz/{quizId}/{qId}/")
    public String getQuestion(@PathVariable String quizId, @PathVariable String qId, Model m) {
        try {
            RunningQuestion runningQuestion = quizService.getQuestion(getUserId(), quizId, Integer.parseInt(qId));
            m.addAttribute("question", runningQuestion);
            return "quiz.html";
        } catch (Exception e) {
            log.error("failed", e);
            addWarning(m);
        }
        return null;
    }

    @GetMapping("/quiz/{quizId}/{qId}/{part}")
    public String getQuizPost(@PathVariable String quizId, @PathVariable String qId, @PathVariable Part part, Model m) {
        try {
            Passage passage = quizService.getPassage(getUserId(), quizId, Integer.parseInt(qId), part);
            PassageModel model = PassageModel.from(passage);
            model.setPath(part.name());
            if (part == Part.origin) {
                model.setHtmlClasses("bg-dark bg-opacity-25 border border-primary border-4 p-2");
            }
            if (part != Part.origin && model.getContent() != null) {
                PassageModel.PassageLoader loader = new PassageModel.PassageLoader();
                model.setPassageLoader(loader);
            }
            m.addAttribute("model", model);
        } catch (ApiException e) {
            log.error("failed", e);
            addWarning(m);
        }
        return "passageContextLoading.html";
    }

    @GetMapping("/quiz/{quizId}/{qId}/select/{verseId}")
    public String selectVerseGetResult(@PathVariable String quizId, @PathVariable String qId, @PathVariable String verseId, Model m) {
        try {
            log.debug("selectVerseGetResult() called with: quizId = [" + quizId + "], qId = [" + qId + "], verseId = [" + verseId + "], m = [" + m + "]");

            RunningQuestion runningQuestion = quizService.getQuestion(getUserId(), quizId, Integer.parseInt(qId));
            runningQuestion.setAnsweredAt(LocalDateTime.now());
            runningQuestion.setSelectedVerse(verseId, versesService);

            ResultModel resultModel = new ResultModel();
            resultModel.setVerseDiff(runningQuestion.getDiffVerses(versesService));
            resultModel.setTimespan(runningQuestion.getTimespan());
            resultModel.setPoints(runningQuestion.getPoints(quizService));
            resultModel.setUrlNext("");
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
