package com.devrezaur.main.viewmodel;

import com.devrezaur.main.controller.Part;
import com.devrezaur.main.model.BookWrap;
import com.devrezaur.main.model.ChapterWrap;
import com.devrezaur.main.model.VerseWrap;
import com.devrezaur.main.service.QuizService;
import com.devrezaur.main.service.VersesService;
import de.evangeliumstaucher.invoker.ApiException;
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
@NoArgsConstructor
@Slf4j
public class RunningQuestion {
    int extendingPrePassageCount = 0;
    int extendingPostPassageCount = 0;
    private LocalDateTime startedAt = LocalDateTime.now();
    private LocalDateTime answeredAt;
    private VerseWrap verse;
    private VerseWrap selectedVerse;
    private VerseWrap contextStartVerse;
    private VerseWrap contextEndVerse;
    private PassageModel pre = new PassageModel("", "", Part.pre.name(), new PassageModel.PassageLoader());
    private PassageModel post = new PassageModel("", "", Part.post.name(), new PassageModel.PassageLoader());
    private PassageModel origin = new PassageModel("", "", Part.origin.name(), new PassageModel.PassageLoader().withDelay(0));
    private List<BookModel> books;
    @Getter(AccessLevel.NONE)
    private Integer diffVerses;

    public int getDiffVerses(VersesService versesService) throws ApiException {
        if (diffVerses == null) {
            diffVerses = verse.diffVerses(selectedVerse, versesService);
        }
        return diffVerses;
    }

    public void setSelectedVerse(String verseId, VersesService versesService) throws ApiException {
        List<BookWrap> booklist = verse.getChapter().getBook().getBible().getBooks();
        for (BookWrap bookWrap : booklist) {
            if (verseId.startsWith(bookWrap.getBook().getId())) {
                log.debug("book: " + bookWrap.getBook());
                for (ChapterWrap chapter : bookWrap.getChapters()) {
                    if (verseId.startsWith(chapter.getChapter().getId())) {
                        log.debug("chapter: " + chapter.getChapter());
                        for (VerseWrap verse : chapter.getVerses(versesService)) {
                            if (verseId.startsWith(verse.getVerseSummary().getId())) {
                                log.debug("verse: " + verse.getVerseSummary());
                                setSelectedVerse(verse);
                                return;
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
        throw new IllegalArgumentException("verseId is not valid " + verseId);
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

    public int getPoints(QuizService quizService ) throws ApiException {
        return quizService.calcPoints(this);
    }
}
