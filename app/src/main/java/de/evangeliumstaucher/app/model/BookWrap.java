package de.evangeliumstaucher.app.model;

import de.evangeliumstaucher.repo.model.BibleBook;
import de.evangeliumstaucher.repo.service.Library;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString
public class BookWrap {
    @ToString.Exclude
    private final BibleBook book;
    private final BibleWrap bible;
    @ToString.Exclude
    List<ChapterWrap> chapters;

    public static int diffVerses(VerseWrap from, VerseWrap to, Library library) {
        int diff = 0;
        BookWrap fromBook = from.getChapter().getBook();
        BookWrap toBook = to.getChapter().getBook();
        if (fromBook.getIndex(library) < toBook.getIndex(library)) {
            // verses in chapter
            // diff += from.diffVerses(from.getChapter().getLast(versesService), versesService);
            // remaining verses in book
            diff += ChapterWrap.diffVerses(from, fromBook.getLast().getLast(library), library);
            // verses of all books between
            int sum = 0;
            for (BookWrap b : fromBook.getBible().getBooks(library)) {
                if (fromBook.getIndex(library) < b.getIndex(library) && b.getIndex(library) < toBook.getIndex(library)) {
                    int versesCount = b.getVersesCount(library);
                    sum += versesCount;
                }
            }
            diff += sum;
        } else if (fromBook.getIndex(library) > toBook.getIndex(library)) {
            // verses in chapter
            // diff += from.diffVerses(from.getChapter().getVerses(versesService).get(0), versesService);
            // remaining verses in book
            diff += ChapterWrap.diffVerses(from, fromBook.getChapters().get(0).getVerses(library).get(0), library);
            // verses of all books between
            int sum = 0;
            for (BookWrap b : fromBook.getBible().getBooks(library)) {
                if (toBook.getIndex(library) < b.getIndex(library) && b.getIndex(library) < fromBook.getIndex(library)) {
                    int versesCount = b.getVersesCount(library);
                    sum += versesCount;
                }
            }
            diff += sum;
        }
        return diff;
    }

    public int getIndex(Library library) {
        return bible.getBooks(library).indexOf(this);
    }

    public List<ChapterWrap> getChapters( ) {
        if (chapters == null) {
            chapters = book.getChapters().stream()
                    .map(chapterSummary -> new ChapterWrap(chapterSummary, this))
                    .collect(Collectors.toList());
        }
        return chapters;
    }

    public BookWrap getPrevious(Library library) {
        int myIndex = bible.getBooks(library).indexOf(this);
        if (myIndex == 0) {
            return null;
        } else {
            return bible.getBooks(library).get(myIndex - 1);
        }
    }

    public BookWrap getNext(Library library) {
        int myIndex = getIndex(library);
        if (myIndex == bible.getBooks(library).size() - 1) {
            return null;
        } else {
            return bible.getBooks(library).get(myIndex + 1);
        }
    }

    private int getVersesCount(Library library) {
        return getChapters().stream()
                .mapToInt(chapterWrap -> {
                    return chapterWrap.getVersesCount(library);
                })
                .sum();
    }

    public ChapterWrap getLast() {
        return getChapters().get(getChapters().size() - 1);
    }
}
