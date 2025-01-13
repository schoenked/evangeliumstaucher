package de.evangeliumstaucher.app.service;

import de.evangeliumstaucher.app.model.*;
import de.evangeliumstaucher.app.utils.ListUtils;
import de.evangeliumstaucher.app.viewmodel.PlayerModel;
import de.evangeliumstaucher.app.viewmodel.QuizModel;
import de.evangeliumstaucher.app.viewmodel.QuizSetupModel;
import de.evangeliumstaucher.app.viewmodel.RunningQuestion;
import de.evangeliumstaucher.entity.GameSessionEntity;
import de.evangeliumstaucher.entity.QuestionEntity;
import de.evangeliumstaucher.repo.GameRepository;
import de.evangeliumstaucher.repo.GameSessionRepository;
import de.evangeliumstaucher.repo.QuestionRepository;
import de.evangeliumstaucher.repo.UserQuestionRepository;
import de.evangeliumstaucher.repo.model.BibleBook;
import de.evangeliumstaucher.repo.model.Chapter;
import de.evangeliumstaucher.repo.model.Division;
import de.evangeliumstaucher.repo.model.Verse;
import de.evangeliumstaucher.repo.service.Library;
import jakarta.annotation.Nonnull;
import org.crosswire.jsword.passage.NoSuchVerseException;
import org.crosswire.jsword.passage.PassageTally;
import org.crosswire.jsword.versification.Versification;
import org.crosswire.jsword.versification.system.SystemGerman;
import org.crosswire.jsword.versification.system.Versifications;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
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

    @Mock(answer = Answers.RETURNS_MOCKS)
    private GameRepository gameRepository;
    @Mock(answer = Answers.RETURNS_MOCKS)
    private QuestionRepository questionRepository;
    @Mock(answer = Answers.RETURNS_MOCKS)
    private GameSessionRepository gameSessionRepository;
    @Mock(answer = Answers.RETURNS_MOCKS)
    private UserQuestionRepository userQuestionRepository;

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
        quizService = new QuizService(library, gameRepository, questionRepository, userQuestionRepository, gameSessionRepository);
        bible = new BibleWrap("", library.getBible(""));
        List<BookWrap> books = IntStream.range(1, 10)
                .mapToObj(iBook -> createBook(iBook, bible))
                .toList();
        bible.setBooks(books);
    }

    @Test
    public void testGetDiff() {
        VerseWrap verse = bible.getBooks(null).get(2).getChapters().get(2).getVerses(library).get(2);
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
            runningQuestion.setVerse(bible.getBooks(null).getFirst().getChapters().getFirst().getVerses(library).getFirst());
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
        runningQuestion.setVerse(bible.getBooks(null).get(2).getChapters().get(2).getVerses(library).get(2));
        runningQuestion.setSelectedVerse(bible.getBooks(null).get(2).getChapters().get(2).getVerses(library).get(1));
        assertThat(runningQuestion.getDiffVerses(library)).isEqualTo(1);

        runningQuestion = getRunningQuestion();
        runningQuestion.setSelectedVerse(bible.getBooks(null).get(2).getChapters().get(1).getVerses(library).get(2));
        runningQuestion.setVerse(bible.getBooks(null).get(2).getChapters().get(2).getVerses(library).get(2));
        runningQuestion.setSelectedVerse(bible.getBooks(null).get(2).getChapters().get(1).getVerses(library).get(2));
        assertThat(runningQuestion.getDiffVerses(library)).isEqualTo(9);

        runningQuestion = getRunningQuestion();
        runningQuestion.setVerse(bible.getBooks(null).get(2).getChapters().get(2).getVerses(library).get(2));
        runningQuestion.setSelectedVerse(bible.getBooks(null).get(4).getChapters().get(1).getVerses(library).getFirst());
        assertThat(runningQuestion.getDiffVerses(library)).isEqualTo(151);
    }

    @Test
    void getTags() {
        List<String> tags = QuizService.getTags("  asdf fdsa,bär,12,2,345x;ws/bible#x#blessed");
        assertThat(tags).isEqualTo(List.of(
                "asdf",
                "fdsa",
                "12",
                "345x",
                "ws",
                "bible",
                "blessed"
        ));
    }

    @RepeatedTest(100)
    public void testBlackAndWhiteli() throws NoSuchVerseException {
        BibleWrap biblewrap = PassageTest.getBibleWrap(library);
        QuizSetupModel model = QuizSetupModel.from(biblewrap, library);
        model.setCountVerses(1);
        model.setTags("");
        String v1 = getRandomPassage(model.getPassageTree());
        String v2 = getRandomPassage(model.getPassageTree());
        String v3 = getRandomPassage(model.getPassageTree());
        String whitelist = "%s,%s,%s".formatted(v1, v2, v3);
        String blacklist = v3;
        QuizModel response = quizService.createQuiz(biblewrap, model, new PlayerModel(), whitelist, blacklist);

        if (response!= null && !response.getVerses().isEmpty()) {
            Versification versification = Versifications.instance().getVersification(SystemGerman.V11N_NAME);

            boolean filterCorrect = new org.crosswire.jsword.passage.PassageTally(versification, whitelist)
                    .contains(new PassageTally(versification, response.getVerses().get(0).getId()));

            boolean blacklistFilterCorrect = !new org.crosswire.jsword.passage.PassageTally(versification, blacklist)
                    .contains(new PassageTally(versification, response.getVerses().get(0).getId()));

            assertThat(filterCorrect).isTrue();
            assertThat(blacklistFilterCorrect).isTrue();
        }
    }

    private String getRandomPassage(PassageTree passageTree) {
        if (passageTree.getTree() == null || passageTree.getTree().isEmpty()) {
            return passageTree.getId();
        }
        return getRandomPassage(ListUtils.randomItem(passageTree.getTree()));
    }

}