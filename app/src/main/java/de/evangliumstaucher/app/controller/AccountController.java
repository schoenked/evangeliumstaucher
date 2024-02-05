package de.evangliumstaucher.app.controller;

import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Book;
import de.evangeliumstaucher.model.Passage;
import de.evangeliumstaucher.model.VerseSummary;
import de.evangliumstaucher.app.service.*;
import de.evangliumstaucher.app.viewmodel.BookModel;
import de.evangliumstaucher.app.viewmodel.PassageModel;
import de.evangliumstaucher.app.viewmodel.VerseModel;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.common.reflection.qual.GetConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller()
@Slf4j
public class AccountController extends BaseController {

    public AccountController(BibleService bibleService, BookService bookService, ChaptersService chaptersService, VersesService versesService, PassageService passageService) {
        super(bibleService, bookService, chaptersService, versesService, passageService);
    }

    @GetMapping("/signup")
    public String signup(Model m) {
        return "signup.html";
    }

}
