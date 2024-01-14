package com.devrezaur.main.model;

import com.devrezaur.main.service.VersesService;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Book;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BookWrap {
    private final Book book;
    private final BibleWrap bible;
    List<ChapterWrap> chapters;

    public static int diffVerses(VerseWrap from, VerseWrap to, VersesService versesService) throws ApiException {
        int diff = 0;
        BookWrap fromBook = from.getChapter().getBook();
        BookWrap toBook = to.getChapter().getBook();
        if (fromBook.getIndex() < toBook.getIndex()) {
            // verses in chapter
            // diff += from.diffVerses(from.getChapter().getLast(versesService), versesService);
            // remaining verses in book
            diff += ChapterWrap.diffVerses(from, fromBook.getLast().getLast(versesService), versesService);
            // verses of all books between
            diff += fromBook.getBible().getBooks().stream()
                    .filter(b -> fromBook.getIndex() < b.getIndex() && b.getIndex() < toBook.getIndex())
                    .mapToInt(bookWrap -> bookWrap.getVersesCount(versesService))
                    .sum();
        } else if (fromBook.getIndex() > toBook.getIndex()) {
            // verses in chapter
            // diff += from.diffVerses(from.getChapter().getVerses(versesService).get(0), versesService);
            // remaining verses in book
            diff += ChapterWrap.diffVerses(from, fromBook.getChapters().get(0).getVerses(versesService).get(0), versesService);
            // verses of all books between
            diff += fromBook.getBible().getBooks().stream()
                    .filter(b -> toBook.getIndex() < b.getIndex() && b.getIndex() < fromBook.getIndex())
                    .mapToInt(bookWrap -> bookWrap.getVersesCount(versesService))
                    .sum();
        }
        return diff;
    }

    public int getIndex() {
        return bible.getBooks().indexOf(this);
    }

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
        int myIndex = getIndex();
        if (myIndex == bible.getBooks().size() - 1) {
            return null;
        } else {
            return bible.getBooks().get(myIndex + 1);
        }
    }

    private int getVersesCount(VersesService versesService) {
        return chapters.stream()
                .mapToInt(chapterWrap -> {
                    try {
                        return chapterWrap.getVersesCount(versesService);
                    } catch (ApiException e) {
                        throw new RuntimeException(e);
                    }
                })
                .sum();
    }

    public ChapterWrap getLast() {
        return chapters.get(chapters.size() - 1);
    }
}
