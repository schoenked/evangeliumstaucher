package com.devrezaur.main.service;

import com.devrezaur.main.model.BibleWrap;
import com.devrezaur.main.model.BookWrap;
import com.devrezaur.main.model.ChapterWrap;
import com.devrezaur.main.model.VerseWrap;
import de.evangeliumstaucher.model.Book;
import de.evangeliumstaucher.model.ChapterSummary;
import de.evangeliumstaucher.model.VerseSummary;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

public class QuizServiceTest {

    static QuizService quizService;
    private static BibleWrap bible;

    private static BookWrap createBook(int iBook, BibleWrap bible) {

        BookWrap book = new BookWrap(new Book().id(String.valueOf(iBook)), bible);
        book.setChapters(IntStream.range(1, 10)
                .mapToObj(iChapter -> {
                            ChapterWrap chapter = new ChapterWrap(new ChapterSummary().id(iBook + "." + iChapter), book);
                            List<VerseWrap> verses = IntStream.range(1, 10)
                                    .mapToObj(iVerse -> {
                                                return new VerseWrap(chapter, new VerseSummary().id(iBook + "." + iChapter + "." + iVerse));
                                            }
                                    )
                                    .toList();
                            chapter.setVerses(verses);
                            return chapter;
                        }
                ).toList());
        return book;
    }

    @BeforeAll
    public static void setup() {
        quizService = new QuizService(null, null, null, null, null);
        bible = new BibleWrap();
        List<BookWrap> books = IntStream.range(1, 10)
                .mapToObj(iBook -> createBook(iBook, bible))
                .toList();
        bible.setBooks(books);

    }

    @Test
    public void testGetDiff() {
      /*  quizService.getVerse(1,
                bible.getBooksWrapped().get(2).getChapterWrapList().get(2).getVerses().get(2));*/
    }

}