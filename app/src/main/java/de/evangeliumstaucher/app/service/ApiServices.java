package de.evangeliumstaucher.app.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class ApiServices {
    final BookService bookService;
    final BibleService bibleService;
    final PassageService passageService;
    final VersesService versesService;
    final ChaptersService chaptersService;
}
