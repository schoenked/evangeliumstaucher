package de.evangeliumstaucher.app.controller;

import com.google.common.base.Strings;
import de.evangeliumstaucher.app.utils.ListUtils;
import de.evangeliumstaucher.app.viewmodel.BookModel;
import de.evangeliumstaucher.app.viewmodel.PassageModel;
import de.evangeliumstaucher.app.viewmodel.VerseModel;
import de.evangeliumstaucher.repo.GameSessionRepository;
import de.evangeliumstaucher.repo.model.BibleBook;
import de.evangeliumstaucher.repo.model.Passage;
import de.evangeliumstaucher.repo.model.Verse;
import de.evangeliumstaucher.repo.service.Library;
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

    public BibleController(Library library, GameSessionRepository gameSessionRepository) {
        super(library, gameSessionRepository);
    }

    @GetMapping("/bible/")
    public String getBible(Model m) {
        return super.getBible(m);
    }

    @GetMapping("/bible/{bibleId}/passage/{passageId}")
    public String getPassageView(@PathVariable String bibleId, @PathVariable String passageId, Model m) {
        try {
            Passage passage = library.getPassage(
                    bibleId,
                    passageId
            );
            PassageModel passageModel = PassageModel.from(passage);
            m.addAttribute("passage", passageModel);
        } catch (Exception e) {
            log.error("failed", e);
            addWarning(m);
        }
        return "passage.html";
    }

    @GetMapping("/bible/{bibleId}/{chapterId}")
    public String getVersesView(@PathVariable String bibleId, @PathVariable String chapterId, @RequestParam("prefix") String prefix, Model m) {
        try {
            List<Verse> verses = library.getVerses(bibleId, chapterId);
            List<VerseModel> veseeModels = verses.stream()
                    .map(VerseModel::from)
                    .peek(v -> {
                        if (!Strings.isNullOrEmpty(prefix)) {
                            v.setUrl("./" + prefix + "/" + v.getUrl());
                        }
                    })
                    .collect(Collectors.toList());
            m.addAttribute("verseGroups", ListUtils.groupsOf(veseeModels, 5));
        } catch (Exception e) {
            log.error("failed", e);
            addWarning(m);
        }
        return "verses.html";
    }

    @GetMapping("/bible/{bibleId}")
    public String getBooks(@PathVariable String bibleId, Model m) {
        try {
            List<? extends BibleBook> books = library.getBibleBooks(bibleId);
            List<BookModel> bookModels = BookModel.from(books, "passage", library);
            m.addAttribute("books", bookModels);
        } catch (Exception e) {
            log.error("failed", e);
            addWarning(m);
        }
        return "books.html";
    }

}
