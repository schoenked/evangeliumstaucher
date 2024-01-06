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

    public BookWrap getPrevious() {
        int myIndex = bible.getBooks().indexOf(this);
        if (myIndex == 0) {
            return null;
        } else {
            return bible.getBooks().get(myIndex - 1);
        }
    }

    public BookWrap getNext() {
        int myIndex = bible.getBooks().indexOf(this);
        if (myIndex == bible.getBooks().size() - 1) {
            return null;
        } else {
            return bible.getBooks().get(myIndex + 1);
        }
    }
}
