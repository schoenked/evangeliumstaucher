package com.devrezaur.main.viewmodel;

import com.devrezaur.main.controller.Part;
import com.devrezaur.main.model.BookWrap;
import com.devrezaur.main.model.ChapterWrap;
import com.devrezaur.main.model.VerseWrap;
import com.devrezaur.main.service.VersesService;
import de.evangeliumstaucher.invoker.ApiException;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private Integer diffVerses;

    public int diffVerses(VersesService versesService) throws ApiException {
        if (diffVerses == null) {
            diffVerses = verse.diffVerses(selectedVerse, versesService);
        }
        return diffVerses;
    }

    public void setSelectedVerse(String verseId, VersesService versesService) throws ApiException {
        List<BookWrap> booklist = verse.getChapter().getBook().getBible().getBooks();
        for (BookWrap bookWrap : booklist) {
            if (verseId.startsWith(bookWrap.getBook().getId())) {
                for (ChapterWrap chapter : bookWrap.getChapters()) {
                    if (verseId.startsWith(chapter.getChapter().getId())) {
                        for (VerseWrap verse : chapter.getVerses(versesService)) {
                            if (verseId.startsWith(verse.getVerseSummary().getId())) {
                                setSelectedVerse(verse);
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            }
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

    private Duration getDuration() {
        return Duration.between(getStartedAt(), getAnsweredAt());
    }

    public int getPoints(VersesService versesService) throws ApiException {
        return (int) (Math.pow(getDuration().getSeconds(), 2) + diffVerses(versesService));
    }
}
