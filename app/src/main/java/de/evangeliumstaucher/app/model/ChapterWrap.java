package de.evangeliumstaucher.app.model;

import de.evangeliumstaucher.repo.model.Chapter;
import de.evangeliumstaucher.repo.model.Verse;
import de.evangeliumstaucher.repo.service.Library;
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
    private final Chapter chapter;
    @ToString.Exclude
    private final BookWrap book;
    @ToString.Exclude
    @Setter
    List<VerseWrap> verses;

    public static int diffVerses(VerseWrap from, VerseWrap to, Library library )   {
        int diff = 0;
        ChapterWrap fromChapter = from.getChapter();
        BookWrap fromBook = fromChapter.getBook();
        ChapterWrap toChapter = to.getChapter();
        BookWrap toBook = toChapter.getBook();
        if (fromBook != toBook) {
            diff += BookWrap.diffVerses(from, to, library);
            diff++;
            if (fromBook.getIndex(library) < toBook.getIndex(library)) {
                diff += diffVerses(toBook.getChapters().get(0).getVerses(library).get(0), to, library);
            } else {
                diff += diffVerses(to, toBook.getLast().getLast(library), library);
            }
        } else {
            if (fromChapter.getIndex() < toChapter.getIndex()) {
                // verses in chapter
                diff += from.diffVerses(fromChapter.getLast(library), library);
                // verses of all chapters between
                diff += fromBook.getChapters().stream()
                        .filter(c -> fromChapter.getIndex() < c.getIndex() && c.getIndex() < toChapter.getIndex())
                        .mapToInt(chapterWrap -> {
                                return chapterWrap.getVerses(library).size();
                        })
                        .sum();
                diff += to.getIndex(library) + 1;
            } else if (fromChapter.getIndex() > toChapter.getIndex()) {
                // verses in chapter
                diff += from.diffVerses(fromChapter.getVerses(library).get(0), library);
                // verses of all chapters between
                diff += fromBook.getChapters().stream()
                        .filter(c -> toChapter.getIndex() < c.getIndex() && c.getIndex() < fromChapter.getIndex())
                        .mapToInt(chapterWrap -> {
                      
                                return chapterWrap.getVerses(library).size();
                        
                        })
                        .sum();
                diff += Math.abs(to.getIndex(library) - toChapter.getLast(library).getIndex(library));
                diff++;
            } else {
                diff += from.diffVerses(to, library);
            }
        }

        return diff;
    }

    public int getIndex() {
        return book.getChapters().indexOf(this);
    }

    public List<VerseWrap> getVerses(Library library)   {
        if (verses == null) {
            List<Verse> versesLoaded = library.getVerses(chapter.getBibleId(), chapter.getId());
            verses = versesLoaded.stream()
                    .map(item -> new VerseWrap(this, item))
                    .collect(Collectors.toList());
        }
        return verses;
    }

    public ChapterWrap getPrevious(Library library)   {
        int myIndex = book.getChapters().indexOf(this);
        if (myIndex == 0) {
            BookWrap previous = book.getPrevious(library);
            if (previous == null) {
                return null;
            }
            return previous.getChapters().get(previous.getChapters().size() - 1);
        } else {
            return book.getChapters().get(myIndex - 1);
        }
    }

    public ChapterWrap getNext(Library library)   {
        int myIndex = getIndex();
        if (myIndex == book.getChapters().size() - 1) {
            BookWrap next = book.getNext(library);
            if (next == null) {
                return null;
            }
            return next.getChapters().get(0);
        } else {
            return book.getChapters().get(myIndex + 1);
        }
    }

    public VerseWrap getLast(Library library)   {
        return getVerses(library).get(getVerses(library).size() - 1);
    }

    public int getVersesCount(Library library)   {
        return getVerses(library).size();
    }
}
