package com.devrezaur.main.model;

import de.evangeliumstaucher.model.Book;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BookWrap {
    private final Book book;
    private final BibleWrap bible;
    List<ChapterWrap> chapters;

    public List<ChapterWrap> getChapters() {
        if (chapters == null) {
            chapters = book.getChapters().stream()
                    .map(chapterSummary -> new ChapterWrap(chapterSummary, this))
                    .collect(Collectors.toList());
        }
        return chapters;
    }
}
