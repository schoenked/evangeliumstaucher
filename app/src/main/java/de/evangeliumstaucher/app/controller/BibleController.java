package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.service.ApiServices;
import de.evangeliumstaucher.app.utils.ListUtils;
import de.evangeliumstaucher.app.viewmodel.BookModel;
import de.evangeliumstaucher.app.viewmodel.PassageModel;
import de.evangeliumstaucher.app.viewmodel.VerseModel;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Book;
import de.evangeliumstaucher.model.Passage;
import de.evangeliumstaucher.model.VerseSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller()
@Slf4j
public class BibleController extends BaseController {

    public BibleController(ApiServices apiServices) {
        super(apiServices);
    }

    @GetMapping("/bible/")
    public String getBible(Model m) {
        return super.getBible(m);
    }

    @GetMapping("/bible/{bibleId}/passage/{passageId}")
    public String getPassageView(@PathVariable String bibleId, @PathVariable String passageId, Model m) {
        try {
            Passage passage = apiServices.getPassageService().getPassage(
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
    public String getVersesView(@PathVariable String bibleId, @PathVariable String chapterId, @RequestParam("prefix") String prefix, Model m) {
        try {
            List<VerseSummary> verses = apiServices.getVersesService().getVerses(bibleId, chapterId);
            List<VerseModel> veseeModels = verses.stream()
                    .map(v -> VerseModel.from(v))
                    .peek(v -> {
                        if (prefix != null) {
                            v.setUrl("./" + prefix + "/" + v.getUrl());
                        }
                    })
                    .collect(Collectors.toList());
            m.addAttribute("verseGroups", ListUtils.groupsOf(veseeModels, 5));
        } catch (ApiException e) {
            log.error("failed", e);
            addWarning(m);
        }
        return "verses.html";
    }

    @GetMapping("/bible/{bibleId}")
    public String getBooks(@PathVariable String bibleId, Model m) {
        try {
            List<Book> books = apiServices.getBookService().getBibleBooks(bibleId);
            List<BookModel> bookModels = BookModel.from(books, apiServices.getVersesService());
            bookModels.forEach(bookModel -> bookModel.setPrefixVerses("passage"));
            m.addAttribute("books", bookModels);
        } catch (ApiException e) {
            log.error("failed", e);
            addWarning(m);
        }
        return "books.html";
    }

}
