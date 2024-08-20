package de.evangeliumstaucher.app.viewmodel;

import com.google.common.collect.Lists;
import de.evangeliumstaucher.app.model.BibleWrap;
import de.evangeliumstaucher.app.model.BookWrap;
import de.evangeliumstaucher.app.model.ChapterWrap;
import de.evangeliumstaucher.app.model.VerseWrap;
import de.evangeliumstaucher.app.service.QuizService;
import de.evangeliumstaucher.entity.GameSessionEntity;
import de.evangeliumstaucher.entity.QuestionEntity;
import de.evangeliumstaucher.entity.UserQuestionEntity;
import de.evangeliumstaucher.repo.service.Library;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Data
@With
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Slf4j
public class RunningQuestion {
    private final QuestionEntity questionEntity;
    private final GameSessionEntity gameSessionEntity;
    int extendingPrePassageCount = 0;
    int extendingPostPassageCount = 0;
    private LocalDateTime startedAt;
    private LocalDateTime answeredAt;
    private VerseWrap verse;
    private VerseWrap selectedVerse;
    private VerseWrap contextStartVerse;
    private VerseWrap contextEndVerse;
    private PassageModel pre = new PassageModel(Part.pre.name(), new PassageModel.PassageLoader());
    private PassageModel post = new PassageModel(Part.post.name(), new PassageModel.PassageLoader());
    private PassageModel origin = new PassageModel(Part.origin.name(), new PassageModel.PassageLoader().withDelay(0));
    private List<BookModel> books;
    @Getter(AccessLevel.NONE)
    private Integer diffVerses;
    private String url;
    private Integer points;
    private int countQuestions;
    private long indexQuestion;

    public static VerseWrap getVerse(String verseId, BibleWrap bibleWrap, Library library) {
        for (BookWrap bookWrap : bibleWrap.getBooks(library)) {
            if (verseId.startsWith(bookWrap.getBook().getId())) {
                log.debug("book: " + bookWrap.getBook().getId());
                for (ChapterWrap chapter : Lists.reverse(bookWrap.getChapters())) {
                    if (verseId.startsWith(chapter.getChapter().getId())) {
                        log.debug("chapter: " + chapter.getChapter().getId());
                        for (VerseWrap verse : Lists.reverse(chapter.getVerses(library))) {
                            if (verseId.startsWith(verse.getVerse().getId())) {
                                log.debug("verse: " + verse.getVerse().getId());
                                return verse;
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
        return null;
    }

    public int getDiffVerses(Library library) {
        if (diffVerses == null) {
            diffVerses = verse.diffVerses(selectedVerse, library);
        }
        return diffVerses;
    }

    public void setSelectedVerse(String verseId, Library library) {
        VerseWrap set = getVerse(verseId, verse.getChapter().getBook().getBible(), library);
        if (set != null) {
            setSelectedVerse(set);
        } else {
            throw new IllegalArgumentException("verseId is not valid " + verseId);
        }
    }

    public String getTimespan() {
        Duration duration = getDuration();

        long seconds = duration.getSeconds(); // Gets the number of seconds in the duration (123)
        String minuteSuffix = "n"; // Defaults to "n" for the plural suffix
        String secondSuffix = "n"; // Defaults to "n" for the plural suffix
        if (seconds / 60 == 1) { // If the minute component is 1, removes the "n" suffix
            minuteSuffix = "";
        }
        if (seconds % 60 == 1) { // If the second component is 1, removes the "n" suffix
            secondSuffix = "";
        }
        String output = String.format(Locale.GERMAN, "%d Minute%s und %d Sekunde%s", seconds / 60, minuteSuffix, seconds % 60, secondSuffix); // Formats the output in German (1 Minute und 1 Sekunde)

        return output;
    }

    public Duration getDuration() {
        return Duration.between(getStartedAt(), getAnsweredAt());
    }

    public Integer getPoints(QuizService quizService) {
        if (points == null) {
            points = quizService.calcPoints(this);
        }
        return points;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long syncEntity(QuizService quizservice, Library library) {
        UserQuestionEntity entity = quizservice.getUserQuestionRepository().findByGameSessionIdAndQuestionId(gameSessionEntity.getId(), questionEntity.getId()).get();
        if (entity.getAnsweredAt() == null) {
            entity.setAnsweredAt(getAnsweredAt());
        }
        this.setAnsweredAt(entity.getAnsweredAt());
        this.setStartedAt(entity.getStartedAt());
        if (entity.getSelectedVerse() == null) {
            entity.setSelectedVerse(getSelectedVerse().getVerse().getId());
            entity.setPoints(getPoints(quizservice));
            entity.setDiffVerses(getDiffVerses(library));
        }
        this.setDiffVerses(entity.getDiffVerses());
        this.setPoints(entity.getPoints());
        quizservice.getUserQuestionRepository().save(entity);
        return entity.getId();
    }

    public QuizModel getQuizModel(Library library) {
        return QuizModel.from(gameSessionEntity.getGame(), library);
    }
}
