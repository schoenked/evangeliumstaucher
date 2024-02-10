package de.evangeliumstaucher.app.model;

import de.evangeliumstaucher.app.service.ApiServices;
import de.evangeliumstaucher.app.service.BookService;
import de.evangeliumstaucher.app.service.VersesService;
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
    @ToString.Exclude
    private final ChapterSummary chapter;
    @ToString.Exclude
    private final BookWrap book;
    @ToString.Exclude
    @Setter
    List<VerseWrap> verses;

    public static int diffVerses(VerseWrap from, VerseWrap to, ApiServices apiServices) throws ApiException {
        int diff = 0;
        ChapterWrap fromChapter = from.getChapter();
        BookWrap fromBook = fromChapter.getBook();
        ChapterWrap toChapter = to.getChapter();
        BookWrap toBook = toChapter.getBook();
        if (fromBook != toBook) {
            diff += BookWrap.diffVerses(from, to, apiServices);
            diff++;
            if (fromBook.getIndex(apiServices) < toBook.getIndex(apiServices)) {
                diff += diffVerses(toBook.getChapters().get(0).getVerses(apiServices.getVersesService()).get(0), to, apiServices);
            } else {
                diff += diffVerses(to, toBook.getLast().getLast(apiServices), apiServices);
            }
        } else {
            if (fromChapter.getIndex() < toChapter.getIndex()) {
                // verses in chapter
                diff += from.diffVerses(fromChapter.getLast(apiServices), apiServices);
                // verses of all chapters between
                diff += fromBook.getChapters().stream()
                        .filter(c -> fromChapter.getIndex() < c.getIndex() && c.getIndex() < toChapter.getIndex())
                        .mapToInt(chapterWrap -> {
                            try {
                                return chapterWrap.getVerses(apiServices.getVersesService()).size();
                            } catch (ApiException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .sum();
                diff += to.getIndex(apiServices) + 1;
            } else if (fromChapter.getIndex() > toChapter.getIndex()) {
                // verses in chapter
                diff += from.diffVerses(fromChapter.getVerses(apiServices.getVersesService()).get(0), apiServices);
                // verses of all chapters between
                diff += fromBook.getChapters().stream()
                        .filter(c -> toChapter.getIndex() < c.getIndex() && c.getIndex() < fromChapter.getIndex())
                        .mapToInt(chapterWrap -> {
                            try {
                                return chapterWrap.getVerses(apiServices.getVersesService()).size();
                            } catch (ApiException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .sum();
                diff += Math.abs(to.getIndex(apiServices) - toChapter.getLast(apiServices).getIndex(apiServices));
                diff++;
            } else {
                diff += from.diffVerses(to, apiServices);
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

    public ChapterWrap getPrevious(BookService bookService) throws ApiException {
        int myIndex = book.getChapters().indexOf(this);
        if (myIndex == 0) {
            BookWrap previous = book.getPrevious(bookService);
            if (previous == null) {
                return null;
            }
            return previous.getChapters().get(previous.getChapters().size() - 1);
        } else {
            return book.getChapters().get(myIndex - 1);
        }
    }

    public ChapterWrap getNext(ApiServices apiServices) throws ApiException {
        int myIndex = getIndex();
        if (myIndex == book.getChapters().size() - 1) {
            BookWrap next = book.getNext(apiServices);
            if (next == null) {
                return null;
            }
            return next.getChapters().get(0);
        } else {
            return book.getChapters().get(myIndex + 1);
        }
    }

    public VerseWrap getLast(ApiServices apiServices) throws ApiException {
        return getVerses(apiServices.getVersesService()).get(getVerses(apiServices.getVersesService()).size() - 1);
    }

    public int getVersesCount(VersesService versesService) throws ApiException {
        return getVerses(versesService).size();
    }
}
