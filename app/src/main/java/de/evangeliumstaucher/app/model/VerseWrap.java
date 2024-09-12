package de.evangeliumstaucher.app.model;

import de.evangeliumstaucher.repo.model.Verse;
import de.evangeliumstaucher.repo.service.Library;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@RequiredArgsConstructor
public class VerseWrap {
    private final ChapterWrap chapter;
    private final Verse verse;

    public static VerseWrap getVerse(String id, BibleWrap bibleWrap, Library library) {
        Verse v = bibleWrap.getBible().getVerse(id);
        ChapterWrap c = null;
        for (BookWrap book : bibleWrap.getBooks(library)) {
            if (id.startsWith(book.getBook().getName())) {
                id = id.substring(book.getBook().getName().length() + 1);
                id = id.split(":")[0];
                for (ChapterWrap chapter : book.getChapters()) {
                    if (id.equals(chapter.getChapter().getNumber())) {
                        c = chapter;
                    }
                }
            }
        }
        Objects.requireNonNull(c, v.toString());
        return new VerseWrap(c, v);
    }

    public int getIndex(Library library) {
        return chapter.getVerses(library).indexOf(this);
    }

    public VerseWrap stepVerses(int steps, Library library) {
        int myIndex = getIndex(library);
        if (steps == 0) {
            return this;
        } else if (steps < 0) {
            if (myIndex == 0) {
                ChapterWrap previous = chapter.getPrevious(library);
                if (previous == null) {
                    return null;
                }
                List<VerseWrap> prevVerses = previous.getVerses(library);
                return prevVerses.get(prevVerses.size() - 1).stepVerses(++steps, library);
            } else {
                return chapter.getVerses(library).get(myIndex - 1).stepVerses(++steps, library);
            }
        } else if (steps > 0) {
            //check if last
            if (myIndex == chapter.getVerses(library).size() - 1) {
                ChapterWrap next = chapter.getNext(library);
                if (next == null) {
                    return null;
                }
                List<VerseWrap> nextVerses = next.getVerses(library);
                return nextVerses.get(0).stepVerses(--steps, library);
            } else {
                return chapter.getVerses(library).get(myIndex + 1).stepVerses(--steps, library);
            }
        }
        return null;
    }

    public int diffVerses(VerseWrap to, Library library) {
        int diff = 0;
        if (this.getChapter() == to.getChapter()) {
            diff += Math.abs(getIndex(library) - to.getIndex(library));
        } else {
            diff += ChapterWrap.diffVerses(this, to, library);
        }
        return diff;
    }

    public String getTextShort() {
        return verse.getTextShort();
    }

    public String getTeXTLong() {
        return verse.getTeXTLong().replace(":", ",");
    }

}
