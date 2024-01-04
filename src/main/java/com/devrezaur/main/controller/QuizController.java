package com.devrezaur.main.controller;

import com.devrezaur.main.service.*;
import com.devrezaur.main.viewmodel.QuizModel;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Passage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

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

    private QuizModel getQuizModel() {
        return (QuizModel) session.getAttribute("QuizSession");
    }

    private void setQuizModel(QuizModel model) {
        session.setAttribute("QuizSession", model);
    }

    @GetMapping("/quiz/{bibleId}/")
    public String getQuiz(@PathVariable String bibleId, Model m) {
        try {
            if (getQuizModel() == null) {
                QuizModel quizModel = quizService.getQuiz(bibleId);
                setQuizModel(quizModel);
                m.addAttribute("quiz", getQuizModel());
                return "quiz.html";
            }
        } catch (ApiException e) {
            log.error("failed", e);
            addWarning(m);
        }
        return null;
    }

    @GetMapping("/quiz/{bibleId}/{part}")
    public String getQuizPost(@PathVariable String bibleId, @PathVariable Part part, Model m) {
        try {
            Passage passage = quizService.getPassage(getQuizModel(), bibleId, part);
            m.addAttribute("text", passage.getContent());
            m.addAttribute("path", part.name());
            m.addAttribute("isLoadingPassage", part != Part.origin);
            m.addAttribute("delayTime", 5000);
        } catch (ApiException e) {
            log.error("failed", e);
            addWarning(m);
        }
        return "passageContextLoading.html";
    }

    @GetMapping("/quiz/")
    public String getQuiz(Model m) {
        return super.getBible(m);
    }
}
