package com.devrezaur.main.model;

import com.devrezaur.main.service.VersesService;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.ChapterSummary;
import de.evangeliumstaucher.model.VerseSummary;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@With
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ChapterWrap {
    private final ChapterSummary chapter;
    private final BookWrap book;

    @Setter
    List<VerseWrap> verses;

    public List<VerseWrap> getVerses(VersesService versesService) throws ApiException {
        if (verses == null) {
            List<VerseSummary> versesLoaded = versesService.getVerses(chapter.getBibleId(), chapter.getId());
            verses = versesLoaded.stream()
                    .map(item -> new VerseWrap(this, item))
                    .collect(Collectors.toList());
        }
        return verses;
    }

    public ChapterWrap getPrevious() {
        int myIndex = book.getChapters().indexOf(this);
        if (myIndex == 0) {
            BookWrap previous = book.getPrevious();
            if (previous == null) {
                return null;
            }
            return previous.getChapters().get(previous.getChapters().size() - 1);
        } else {
            return book.getChapters().get(myIndex - 1);
        }
    }

    public ChapterWrap getNext() {
        int myIndex = book.getChapters().indexOf(this);
        if (myIndex == book.getChapters().size() - 1) {
            BookWrap next = book.getNext();
            if (next == null) {
                return null;
            }    return next.getChapters().get(0);
        } else {
            return book.getChapters().get(myIndex + 1);
        }
    }
}
