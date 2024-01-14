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

    public static int diffVerses(VerseWrap from, VerseWrap to, VersesService versesService) throws ApiException {
        int diff = 0;
        ChapterWrap fromChapter = from.getChapter();
        BookWrap fromBook = fromChapter.getBook();
        ChapterWrap toChapter = to.getChapter();
        BookWrap toBook = toChapter.getBook();
        if (fromBook != toBook) {
            diff += BookWrap.diffVerses(from, to, versesService);
            diff++;
            if (fromBook.getIndex() < toBook.getIndex()) {
                diff += diffVerses(toBook.getChapters().get(0).getVerses(versesService).get(0), to, versesService);
            } else {
                diff += diffVerses(to, toBook.getLast().getLast(versesService), versesService);
            }
        } else {
            if (fromChapter.getIndex() < toChapter.getIndex()) {
                // verses in chapter
                diff += from.diffVerses(fromChapter.getLast(versesService), versesService);
                // verses of all chapters between
                diff += fromBook.getChapters().stream()
                        .filter(c -> fromChapter.getIndex() < c.getIndex() && c.getIndex() < toChapter.getIndex())
                        .mapToInt(chapterWrap -> {
                            try {
                                return chapterWrap.getVerses(versesService).size();
                            } catch (ApiException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .sum();
                diff += to.getIndex(versesService) + 1;
            } else if (fromChapter.getIndex() > toChapter.getIndex()) {
                // verses in chapter
                diff += from.diffVerses(fromChapter.getVerses(versesService).get(0), versesService);
                // verses of all chapters between
                diff += fromBook.getChapters().stream()
                        .filter(c -> toChapter.getIndex() < c.getIndex() && c.getIndex() < fromChapter.getIndex())
                        .mapToInt(chapterWrap -> {
                            try {
                                return chapterWrap.getVerses(versesService).size();
                            } catch (ApiException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .sum();
                diff += Math.abs(to.getIndex(versesService) - toChapter.getLast(versesService).getIndex(versesService));
                diff++;
            } else {
                diff += from.diffVerses(to, versesService);
            }
        }

        return diff;
    }

    public int getIndex() {
        return book.getChapters().indexOf(this);
    }

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
        int myIndex = getIndex();
        if (myIndex == book.getChapters().size() - 1) {
            BookWrap next = book.getNext();
            if (next == null) {
                return null;
            }
            return next.getChapters().get(0);
        } else {
            return book.getChapters().get(myIndex + 1);
        }
    }

    public VerseWrap getLast(VersesService verseService) throws ApiException {
        return getVerses(verseService).get(getVerses(verseService).size() - 1);
    }

    public int getVersesCount(VersesService versesService) throws ApiException {
        return getVerses(versesService).size();
    }
}
