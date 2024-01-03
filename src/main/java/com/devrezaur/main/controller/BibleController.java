package com.devrezaur.main.controller;

import com.devrezaur.main.model.QuestionForm;
import com.devrezaur.main.service.*;
import com.devrezaur.main.viewmodel.BookModel;
import com.devrezaur.main.viewmodel.PassageModel;
import com.devrezaur.main.viewmodel.VerseModel;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Book;
import de.evangeliumstaucher.model.Passage;
import de.evangeliumstaucher.model.VerseSummary;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller()
@Slf4j
public class BibleController extends BaseController {

    public BibleController(BibleService bibleService, BookService bookService, ChaptersService chaptersService, VersesService versesService, PassageService passageService) {
        super(bibleService, bookService, chaptersService, versesService, passageService);
    }

    @GetMapping("/bible")
    public String getBible(Model m) {
        return super.getBible(m);
    }

    @GetMapping("/bible/{bibleId}/passage/{passageId}")
    public String getPassageView(@PathVariable String bibleId, @PathVariable String passageId, Model m) {
        try {
            Passage passage = passageService.getPassage(
                    bibleId,
                    passageId,
                    null,
                    false,
                    false,
                    false,
                    false,
                    false,
                    null,
                    false
            );
            PassageModel passageModel = PassageModel.from(passage);
            m.addAttribute("passage", passageModel);
        } catch (ApiException e) {
            log.error("failed", e);
            addWarning(m);
        }
        return "passage.html";
    }

    @GetMapping("/bible/{bibleId}/{chapterId}")
    public String getVersesView(@PathVariable String bibleId, @PathVariable String chapterId, Model m) {
        try {
            List<VerseSummary> verses = versesService.getVerses(bibleId, chapterId);
            List<VerseModel> veseeModels = verses.stream()
                    .map(v -> VerseModel.from(v))
                    .collect(Collectors.toList());
            m.addAttribute("verses", veseeModels);
        } catch (ApiException e) {
            log.error("failed", e);
            addWarning(m);
        }
        return "verses.html";
    }

    @GetMapping("/bible/{bibleId}")
    public String getBooks(@PathVariable String bibleId, Model m) {
        try {
            List<Book> books = bookService.getBibleBooks(bibleId);
            List<BookModel> bookModels = BookModel.from(books, versesService);
            m.addAttribute("books", bookModels);
        } catch (ApiException e) {
            log.error("failed", e);
            addWarning(m);
        }
        return "books.html";
    }
/*
    //  @PostMapping("quiz")
    public String quiz(@RequestParam String username, Model m, RedirectAttributes ra) {
     *//*   if (username.equals("")) {
            ra.addFlashAttribute("warning", "You must enter your name");
            return "redirect:/";
        }

        QuestionForm qForm = qService.getQuestions();
        m.addAttribute("qForm", qForm);
        m.addAttribute("username", username);
*//*
        return "quiz.html";
    }*/

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
