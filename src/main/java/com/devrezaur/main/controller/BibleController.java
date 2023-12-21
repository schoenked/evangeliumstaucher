package com.devrezaur.main.controller;

import com.devrezaur.main.model.QuestionForm;
import com.devrezaur.main.service.BibleService;
import com.devrezaur.main.service.BookService;
import com.devrezaur.main.service.ChaptersService;
import com.devrezaur.main.service.VersesService;
import com.devrezaur.main.viewmodel.BookModel;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.BibleSummary;
import de.evangeliumstaucher.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Controller()
@RequiredArgsConstructor
public class BibleController {
    private final BibleService bibleService;
    private final BookService bookService;
    private final ChaptersService chaptersService;
    private final VersesService versesApi;

    @GetMapping("/bible")
    public String getBible(Model m) {
        try {
            List<BibleSummary> bibles = bibleService.getBibles();

            List<Map.Entry<String, List<BibleSummary>>> groups = bibles.stream()
                    .collect(groupingBy(bibleSummary -> bibleSummary.getLanguage().getNameLocal()))
                    .entrySet().stream()
                    .sorted((o1, o2) -> {
                        if (o1.getKey().equals("Deutsch")) return -1;
                        if (o2.getKey().equals("Deutsch")) return 1;
                        return o1.getKey().compareTo(o2.getKey());
                    })
                    .collect(Collectors.toList());

            m.addAttribute("languages", groups);
        } catch (ApiException e) {
            addWarning(m);
        }
        return "bible/bible.html";
    }

    @GetMapping("/bible/{bibleId}")
    public String getBooks(@PathVariable String bibleId, Model m) {
        try {
            List<Book> books = bookService.getBibleBooks(bibleId);
            List<BookModel> bookModels = BookModel.from(books);
            m.addAttribute("books", bookModels);
        } catch (ApiException e) {
            addWarning(m);
        }
        return "bible/books.html";
    }

    private void addWarning(Model m) {
        m.addAttribute("warning", "so sorry");
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
