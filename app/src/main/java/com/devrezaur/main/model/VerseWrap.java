package com.devrezaur.main.model;

import com.devrezaur.main.service.VersesService;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.VerseSummary;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class VerseWrap {
    private final ChapterWrap chapter;
    private final VerseSummary verseSummary;

    public int getIndex(VersesService versesService) throws ApiException {
        return chapter.getVerses(versesService).indexOf(this);
    }

    public VerseWrap stepVerses(int steps, VersesService versesService) throws ApiException {
        int myIndex = getIndex(versesService);
        if (steps == 0) {
            return this;
        } else if (steps < 0) {
            if (myIndex == 0) {
                ChapterWrap previous = chapter.getPrevious();
                if (previous == null) {
                    return null;
                }
                List<VerseWrap> prevVerses = previous.getVerses(versesService);
                return prevVerses.get(prevVerses.size() - 1).stepVerses(++steps, versesService);
            } else {
                return chapter.getVerses(versesService).get(myIndex - 1).stepVerses(++steps, versesService);
            }
        } else if (steps > 0) {
            //check if last
            if (myIndex == chapter.getVerses(versesService).size() - 1) {
                ChapterWrap next = chapter.getNext();
                if (next == null) {
                    return null;
                }
                List<VerseWrap> nextVerses = next.getVerses(versesService);
                return nextVerses.get(0).stepVerses(--steps, versesService);
            } else {
                return chapter.getVerses(versesService).get(myIndex + 1).stepVerses(--steps, versesService);
            }
        }
        return null;
    }

    public int diffVerses(VerseWrap to, VersesService versesService) throws ApiException {
        int diff = 0;
        if (this.getChapter() == to.getChapter()) {
            diff += Math.abs(getIndex(versesService) - to.getIndex(versesService));
        } else {
            diff += getChapter().diffVerses(this, to, versesService);
        }
        return diff;
    }

    public String getText() {
        return verseSummary.getReference();
    }
}
