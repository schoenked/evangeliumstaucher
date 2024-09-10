package de.evangeliumstaucher.app.service;

import de.evangeliumstaucher.app.model.BibleWrap;
import de.evangeliumstaucher.app.model.BookWrap;
import de.evangeliumstaucher.app.model.ChapterWrap;
import de.evangeliumstaucher.app.model.VerseWrap;
import de.evangeliumstaucher.app.viewmodel.RunningQuestion;
import de.evangeliumstaucher.entity.GameSessionEntity;
import de.evangeliumstaucher.entity.QuestionEntity;
import de.evangeliumstaucher.repo.model.BibleBook;
import de.evangeliumstaucher.repo.model.Chapter;
import de.evangeliumstaucher.repo.model.Division;
import de.evangeliumstaucher.repo.model.Verse;
import de.evangeliumstaucher.repo.service.Library;
import jakarta.annotation.Nonnull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
public class QuizServiceTest {

    static QuizService quizService;
    private static BibleWrap bible;
    @Autowired
    public Library library;

    private static BookWrap createBook(int iBook, BibleWrap bible) {

        BookWrap book = new BookWrap(new BibleBook() {

            @Override
            public Collection<Chapter> getChapters() {
                return List.of();
            }

            @Override
            public String getAbbreviation() {
                return "";
            }

            @Override
            public String getName() {
                return "";
            }

            @Override
            public List<Division> getDivisions() {
                return List.of();
            }

            @Override
            public String getId() {
                return String.valueOf(iBook);
            }
        }, bible);
        book.setChapters(IntStream.range(1, 10)
                .mapToObj(iChapter -> {
                            ChapterWrap chapter = new ChapterWrap(new Chapter() {
                                @Override
                                public String getBibleId() {
                                    return "";
                                }

                                @Override
                                public String getNumber() {
                                    return "";
                                }

                                @Override
                                public String getText() {
                                    return "";
                                }

                                @Override
                                public BibleBook getBibleBook() {
                                    return null;
                                }

                                @Override
                                public String getId() {
                                    return iBook + "." + iChapter;
                                }
                            }, book);
                            List<VerseWrap> verses = IntStream.range(1, 10)
                                    .mapToObj(iVerse -> {
                                                return new VerseWrap(chapter, new Verse() {

                                                    @Override
                                                    public String getTextShort() {
                                                        return "";
                                                    }

                                                    @Override
                                                    public String getTeXTLong() {
                                                        return "";
                                                    }

                                                    @Override
                                                    public String getBibleId() {
                                                        return "";
                                                    }

                                                    @Override
                                                    public String getId() {
                                                        return iBook + "." + iChapter + "." + iVerse;
                                                    }
                                                });
                                            }
                                    )
                                    .toList();
                            chapter.setVerses(verses);
                            return chapter;
                        }
                ).toList());
        return book;
    }

    @Nonnull
    private static RunningQuestion getRunningQuestion() {
        return new RunningQuestion(new QuestionEntity(), new GameSessionEntity());
    }

    @BeforeEach
    public void setup() {
        quizService = new QuizService(library, null, null, null, null);
        bible = new BibleWrap("", library.getBible(""));
        List<BookWrap> books = IntStream.range(1, 10)
                .mapToObj(iBook -> createBook(iBook, bible))
                .toList();
        bible.setBooks(books);
    }

    @Test
    public void testGetDiff() {
        VerseWrap verse = bible.getBooks(null).get(2).getChapters().get(2).getVerses().get(2);
        VerseWrap stepped = verse.stepVerses(-1, library);
        assertThat(stepped.getVerse().getId()).isEqualTo("3.3.2");
        verse = stepped;
        stepped = verse.stepVerses(-1, library);
        assertThat(stepped.getVerse().getId()).isEqualTo("3.3.1");
        verse = stepped;
        stepped = verse.stepVerses(-1, library);
        assertThat(stepped.getVerse().getId()).isEqualTo("3.2.9");
        verse = stepped;
        stepped = verse.stepVerses(10, library);
        assertThat(stepped.getVerse().getId()).isEqualTo("3.4.1");
        verse = stepped;
        stepped = verse.stepVerses(-100000, library);
        assertThat(stepped).isNull();
    }

    @Test
    public void testCalcDiff() {
        for (int i = 0; i <= 728; i++) {
            RunningQuestion runningQuestion = getRunningQuestion();
            runningQuestion.setVerse(bible.getBooks(null).getFirst().getChapters().getFirst().getVerses().getFirst());
            VerseWrap v = runningQuestion.getVerse().stepVerses(i, library);
            runningQuestion.setSelectedVerse(v);
            assertThat(runningQuestion.getDiffVerses(library)).isEqualTo(i);
        }

        for (int i = 0; i <= 728; i++) {
            RunningQuestion runningQuestion = getRunningQuestion();
            runningQuestion.setVerse(bible.getLast(library).getLast().getLast(library));
            VerseWrap v = runningQuestion.getVerse().stepVerses(i * -1, library);
            runningQuestion.setSelectedVerse(v);
            assertThat(runningQuestion.getDiffVerses(library)).isEqualTo(i);
        }
        RunningQuestion runningQuestion = getRunningQuestion();
        runningQuestion.setVerse(bible.getBooks(null).get(2).getChapters().get(2).getVerses().get(2));
        runningQuestion.setSelectedVerse(bible.getBooks(null).get(2).getChapters().get(2).getVerses().get(1));
        assertThat(runningQuestion.getDiffVerses(library)).isEqualTo(1);

        runningQuestion = getRunningQuestion();
        runningQuestion.setSelectedVerse(bible.getBooks(null).get(2).getChapters().get(1).getVerses().get(2));
        runningQuestion.setVerse(bible.getBooks(null).get(2).getChapters().get(2).getVerses().get(2));
        runningQuestion.setSelectedVerse(bible.getBooks(null).get(2).getChapters().get(1).getVerses().get(2));
        assertThat(runningQuestion.getDiffVerses(library)).isEqualTo(9);

        runningQuestion = getRunningQuestion();
        runningQuestion.setVerse(bible.getBooks(null).get(2).getChapters().get(2).getVerses().get(2));
        runningQuestion.setSelectedVerse(bible.getBooks(null).get(4).getChapters().get(1).getVerses().getFirst());
        assertThat(runningQuestion.getDiffVerses(library)).isEqualTo(151);
    }

}