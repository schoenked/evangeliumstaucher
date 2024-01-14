package com.devrezaur.main.service;

import com.devrezaur.main.model.BibleWrap;
import com.devrezaur.main.model.BookWrap;
import com.devrezaur.main.model.ChapterWrap;
import com.devrezaur.main.model.VerseWrap;
import com.devrezaur.main.viewmodel.RunningQuestion;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Book;
import de.evangeliumstaucher.model.ChapterSummary;
import de.evangeliumstaucher.model.VerseSummary;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static com.google.common.truth.Truth.assertThat;

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
    public void testGetDiff() throws ApiException {
        VerseWrap verse = bible.getBooks().get(2).getChapters().get(2).getVerses().get(2);
        VerseWrap stepped = verse.stepVerses(-1, null);
        assertThat(stepped.getVerseSummary().getId()).isEqualTo("3.3.2");
        verse = stepped;
        stepped = verse.stepVerses(-1, null);
        assertThat(stepped.getVerseSummary().getId()).isEqualTo("3.3.1");
        verse = stepped;
        stepped = verse.stepVerses(-1, null);
        assertThat(stepped.getVerseSummary().getId()).isEqualTo("3.2.9");
        verse = stepped;
        stepped = verse.stepVerses(10, null);
        assertThat(stepped.getVerseSummary().getId()).isEqualTo("3.4.1");
        verse = stepped;
        stepped = verse.stepVerses(-100000, null);
        assertThat(stepped).isNull();
    }

    @Test
    public void testCalcDiff() throws ApiException {
        RunningQuestion runningQuestion = new RunningQuestion();
        runningQuestion.setVerse(bible.getBooks().get(0).getChapters().get(0).getVerses().get(0));
        for (int i = 0; i <= 728; i++) {
            VerseWrap v = runningQuestion.getVerse().stepVerses(i, null);
            runningQuestion.setSelectedVerse(v);
            assertThat(runningQuestion.diffVerses(null)).isEqualTo(i);
        }

        runningQuestion.setVerse(bible.getLast().getLast().getLast(null));
        for (int i = 0; i <= 728; i++) {
            VerseWrap v = runningQuestion.getVerse().stepVerses(i * -1, null);
            runningQuestion.setSelectedVerse(v);
            assertThat(runningQuestion.diffVerses(null)).isEqualTo(i);
        }

        runningQuestion.setVerse(bible.getBooks().get(0).getChapters().get(0).getVerses().get(0));

        runningQuestion.setVerse(bible.getBooks().get(2).getChapters().get(2).getVerses().get(2));
        runningQuestion.setSelectedVerse(bible.getBooks().get(2).getChapters().get(2).getVerses().get(1));
        assertThat(runningQuestion.diffVerses(null)).isEqualTo(1);
        runningQuestion.setSelectedVerse(bible.getBooks().get(2).getChapters().get(1).getVerses().get(2));
        assertThat(runningQuestion.diffVerses(null)).isEqualTo(9);
        runningQuestion.setSelectedVerse(bible.getBooks().get(4).getChapters().get(1).getVerses().get(0));
        assertThat(runningQuestion.diffVerses(null)).isEqualTo(151);
    }

}