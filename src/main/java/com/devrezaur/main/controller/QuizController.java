package com.devrezaur.main.controller;

import com.devrezaur.main.service.*;
import com.devrezaur.main.viewmodel.QuizModel;
import de.evangeliumstaucher.invoker.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller()
@Slf4j
@RequiredArgsConstructor
public class QuizController extends BaseController {
    private final BibleService bibleService;
    private final BookService bookService;
    private final ChaptersService chaptersService;
    private final VersesService versesService;
    private final PassageService passageService;
    private final QuizService quizService;

    @GetMapping("/quiz/{bibleId}")
    public String getQuiz(@PathVariable String bibleId, Model m) {
        try {
            QuizModel quizModel = quizService.getQuiz(bibleId);
            m.addAttribute("quiz", quizModel);
        } catch (ApiException e) {
            log.error("failed", e);
            addWarning(m);
        }
        return "quiz.html";
    }
}
