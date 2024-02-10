package de.evangeliumstaucher.app.model;

import de.evangeliumstaucher.app.service.ApiServices;
import de.evangeliumstaucher.app.service.BookService;
import de.evangeliumstaucher.app.service.VersesService;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Book;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString
public class BookWrap {
    @ToString.Exclude
    private final Book book;
    private final BibleWrap bible;
    @ToString.Exclude
    List<ChapterWrap> chapters;

    public static int diffVerses(VerseWrap from, VerseWrap to, ApiServices apiServices) throws ApiException {
        int diff = 0;
        BookWrap fromBook = from.getChapter().getBook();
        BookWrap toBook = to.getChapter().getBook();
        if (fromBook.getIndex(apiServices) < toBook.getIndex(apiServices)) {
            // verses in chapter
            // diff += from.diffVerses(from.getChapter().getLast(versesService), versesService);
            // remaining verses in book
            diff += ChapterWrap.diffVerses(from, fromBook.getLast().getLast(apiServices), apiServices);
            // verses of all books between
            int sum = 0;
            for (BookWrap b : fromBook.getBible().getBooks(apiServices.getBookService())) {
                if (fromBook.getIndex(apiServices) < b.getIndex(apiServices) && b.getIndex(apiServices) < toBook.getIndex(apiServices)) {
                    int versesCount = b.getVersesCount(apiServices.getVersesService());
                    sum += versesCount;
                }
            }
            diff += sum;
        } else if (fromBook.getIndex(apiServices) > toBook.getIndex(apiServices)) {
            // verses in chapter
            // diff += from.diffVerses(from.getChapter().getVerses(versesService).get(0), versesService);
            // remaining verses in book
            diff += ChapterWrap.diffVerses(from, fromBook.getChapters().get(0).getVerses(apiServices.getVersesService()).get(0), apiServices);
            // verses of all books between
            int sum = 0;
            for (BookWrap b : fromBook.getBible().getBooks(apiServices.getBookService())) {
                if (toBook.getIndex(apiServices) < b.getIndex(apiServices) && b.getIndex(apiServices) < fromBook.getIndex(apiServices)) {
                    int versesCount = b.getVersesCount(apiServices.getVersesService());
                    sum += versesCount;
                }
            }
            diff += sum;
        }
        return diff;
    }

    public int getIndex(ApiServices apiServices) throws ApiException {
        return bible.getBooks(apiServices.getBookService()).indexOf(this);
    }

    public List<ChapterWrap> getChapters() {
        if (chapters == null) {
            chapters = book.getChapters().stream()
                    .map(chapterSummary -> new ChapterWrap(chapterSummary, this))
                    .collect(Collectors.toList());
        }
        return chapters;
    }

    public BookWrap getPrevious(BookService bookService) throws ApiException {
        int myIndex = bible.getBooks(bookService).indexOf(this);
        if (myIndex == 0) {
            return null;
        } else {
            return bible.getBooks(bookService).get(myIndex - 1);
        }
    }

    public BookWrap getNext(ApiServices apiServices) throws ApiException {
        int myIndex = getIndex(apiServices);
        if (myIndex == bible.getBooks(apiServices.getBookService()).size() - 1) {
            return null;
        } else {
            return bible.getBooks(apiServices.getBookService()).get(myIndex + 1);
        }
    }

    private int getVersesCount(VersesService versesService) {
        return getChapters().stream()
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
        return getChapters().get(getChapters().size() - 1);
    }
}
