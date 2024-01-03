package com.devrezaur.main.controller;

import com.devrezaur.main.QuizSession;
import com.devrezaur.main.service.*;
import com.devrezaur.main.viewmodel.QuizModel;
import de.evangeliumstaucher.invoker.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@Slf4j
@SessionAttributes("QuizSession")
public class QuizController extends BaseController {
    private final QuizService quizService;

    public QuizController(BibleService bibleService, BookService bookService, ChaptersService chaptersService, VersesService versesService, PassageService passageService, QuizService quizService) {
        super(bibleService, bookService, chaptersService, versesService, passageService);
        this.quizService = quizService;
    }

    @ModelAttribute("QuizSession")
    public QuizSession quizSession() {
        return new QuizSession();
    }

    @GetMapping("/quiz/{bibleId}")
    public String getQuiz(@PathVariable String bibleId, @ModelAttribute("QuizSession") QuizSession quizSession, Model m) {
        try {
            if (quizSession.getCurrentQuiz() == null) {
                QuizModel quizModel = quizService.getQuiz(bibleId);
                quizSession.setCurrentQuiz(quizModel);
                m.addAttribute("quiz", quizSession.getCurrentQuiz());
                return "quiz.html";
            } else {
                m.addAttribute("text", "the verse we are looking for");
                return "passageContextLoading.html";
            }
        } catch (ApiException e) {
            log.error("failed", e);
            addWarning(m);
        }
        return null;
    }

    @GetMapping("/quiz/{bibleId}/post")
    public String getQuizPost(@PathVariable String bibleId, @ModelAttribute("QuizSession") QuizSession quizSession, Model m) {
        try {
            if (quizSession.getCurrentQuiz() == null) {
                QuizModel quizModel = quizService.getQuiz(bibleId);
                quizSession.setCurrentQuiz(quizModel);
            }
            m.addAttribute("text", "post");
        } catch (ApiException e) {
            log.error("failed", e);
        }
        return "passageContextLoading.html";
    }

    @GetMapping("/quiz")
    public String getQuiz(Model m) {
        return super.getBible(m);
    }
}
