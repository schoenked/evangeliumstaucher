package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.service.*;
import de.evangeliumstaucher.app.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/error")
@Slf4j
public class ErrorController extends BaseController {

    public ErrorController(BibleService bibleService, BookService bookService, ChaptersService chaptersService, VersesService versesService, PassageService passageService) {
        super(bibleService, bookService, chaptersService, versesService, passageService);
    }

    @GetMapping("/error")
    public String getBible(Model m) {
        return "error.html";
    }
}
