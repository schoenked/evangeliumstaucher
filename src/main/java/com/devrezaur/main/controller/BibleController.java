package com.devrezaur.main.controller;

import com.devrezaur.main.model.QuestionForm;
import com.devrezaur.main.model.Result;
import com.devrezaur.main.repository.ResultRepo;
import com.devrezaur.main.service.BibleService;
import com.devrezaur.main.service.QuizService;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.BibleSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller()
@RequiredArgsConstructor
public class BibleController {
    private final BibleService bibleService;

    @GetMapping("/bible")
    public String getBible(Model m) {
        try {
            List<BibleSummary> bibles = bibleService.getBibles();

            m.addAttribute("bibles", bibles);
        } catch (ApiException e) {
           addWarning(m);
        }
        return "bible/bible.html";
    }

    private void addWarning(Model m) {
        m.addAttribute("warning","so sorry");
    }

    //  @PostMapping("quiz")
    public String quiz(@RequestParam String username, Model m, RedirectAttributes ra) {
     /*   if (username.equals("")) {
            ra.addFlashAttribute("warning", "You must enter your name");
            return "redirect:/";
        }

        QuestionForm qForm = qService.getQuestions();
        m.addAttribute("qForm", qForm);
        m.addAttribute("username", username);
*/
        return "quiz.html";
    }

    //@PostMapping("submit")
    public String submit(@ModelAttribute QuestionForm qForm, Model m) {
    /*    Result result = new Result();
        Object username = m.getAttribute("username");
        result.setUsername(username == null ? "" : username.toString());
        result.setTotalCorrect(qService.getResult(qForm));
        qService.saveScore(result);
*/
        return "result.html";
    }

    ////  @GetMapping("/score")
    public String score(Model m) {
     /*   List<Result> sList = qService.getTopScore();
        m.addAttribute("sList", sList);
*/
        return "scoreboard.html";
    }

}
