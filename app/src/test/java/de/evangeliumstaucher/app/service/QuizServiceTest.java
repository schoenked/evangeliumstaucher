package de.evangeliumstaucher.app.service;

import de.evangeliumstaucher.app.model.BibleWrap;
import de.evangeliumstaucher.app.model.BookWrap;
import de.evangeliumstaucher.app.model.ChapterWrap;
import de.evangeliumstaucher.app.model.VerseWrap;
import de.evangeliumstaucher.app.viewmodel.RunningQuestion;
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
    private static ApiServices apiServices;

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
        apiServices = new ApiServices(null, null, null, null, null);
        quizService = new QuizService(apiServices, null);
        bible = new BibleWrap("");
        List<BookWrap> books = IntStream.range(1, 10)
                .mapToObj(iBook -> createBook(iBook, bible))
                .toList();
        bible.setBooks(books);
    }

    @Test
    public void testGetDiff() throws ApiException {
        VerseWrap verse = bible.getBooks(null).get(2).getChapters().get(2).getVerses().get(2);
        VerseWrap stepped = verse.stepVerses(-1, apiServices);
        assertThat(stepped.getVerseSummary().getId()).isEqualTo("3.3.2");
        verse = stepped;
        stepped = verse.stepVerses(-1, apiServices);
        assertThat(stepped.getVerseSummary().getId()).isEqualTo("3.3.1");
        verse = stepped;
        stepped = verse.stepVerses(-1, apiServices);
        assertThat(stepped.getVerseSummary().getId()).isEqualTo("3.2.9");
        verse = stepped;
        stepped = verse.stepVerses(10, apiServices);
        assertThat(stepped.getVerseSummary().getId()).isEqualTo("3.4.1");
        verse = stepped;
        stepped = verse.stepVerses(-100000, apiServices);
        assertThat(stepped).isNull();
    }

    @Test
    public void testCalcDiff() throws ApiException {
        for (int i = 0; i <= 728; i++) {
            RunningQuestion runningQuestion = new RunningQuestion(null);
            runningQuestion.setVerse(bible.getBooks(null).get(0).getChapters().get(0).getVerses().get(0));
            VerseWrap v = runningQuestion.getVerse().stepVerses(i, apiServices);
            runningQuestion.setSelectedVerse(v);
            assertThat(runningQuestion.getDiffVerses(apiServices)).isEqualTo(i);
        }

        for (int i = 0; i <= 728; i++) {
            RunningQuestion runningQuestion = new RunningQuestion(null);
            runningQuestion.setVerse(bible.getLast().getLast().getLast(apiServices));
            VerseWrap v = runningQuestion.getVerse().stepVerses(i * -1, apiServices);
            runningQuestion.setSelectedVerse(v);
            assertThat(runningQuestion.getDiffVerses(apiServices)).isEqualTo(i);
        }
        RunningQuestion runningQuestion = new RunningQuestion(null);
        runningQuestion.setVerse(bible.getBooks(null).get(2).getChapters().get(2).getVerses().get(2));
        runningQuestion.setSelectedVerse(bible.getBooks(null).get(2).getChapters().get(2).getVerses().get(1));
        assertThat(runningQuestion.getDiffVerses(apiServices)).isEqualTo(1);

        runningQuestion = new RunningQuestion(null);
        runningQuestion.setSelectedVerse(bible.getBooks(null).get(2).getChapters().get(1).getVerses().get(2));
        runningQuestion.setVerse(bible.getBooks(null).get(2).getChapters().get(2).getVerses().get(2));
        runningQuestion.setSelectedVerse(bible.getBooks(null).get(2).getChapters().get(1).getVerses().get(2));
        assertThat(runningQuestion.getDiffVerses(apiServices)).isEqualTo(9);

        runningQuestion = new RunningQuestion(null);
        runningQuestion.setVerse(bible.getBooks(null).get(2).getChapters().get(2).getVerses().get(2));
        runningQuestion.setSelectedVerse(bible.getBooks(null).get(4).getChapters().get(1).getVerses().get(0));
        assertThat(runningQuestion.getDiffVerses(apiServices)).isEqualTo(151);
    }

}