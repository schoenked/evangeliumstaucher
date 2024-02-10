package de.evangeliumstaucher.app.model;

import de.evangeliumstaucher.app.service.ApiServices;
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

    public int getIndex(ApiServices apiServices) throws ApiException {
        return chapter.getVerses(apiServices.getVersesService()).indexOf(this);
    }

    public VerseWrap stepVerses(int steps, ApiServices apiServices) throws ApiException {
        int myIndex = getIndex(apiServices);
        if (steps == 0) {
            return this;
        } else if (steps < 0) {
            if (myIndex == 0) {
                ChapterWrap previous = chapter.getPrevious(apiServices.getBookService());
                if (previous == null) {
                    return null;
                }
                List<VerseWrap> prevVerses = previous.getVerses(apiServices.getVersesService());
                return prevVerses.get(prevVerses.size() - 1).stepVerses(++steps, apiServices);
            } else {
                return chapter.getVerses(apiServices.getVersesService()).get(myIndex - 1).stepVerses(++steps, apiServices);
            }
        } else if (steps > 0) {
            //check if last
            if (myIndex == chapter.getVerses(apiServices.getVersesService()).size() - 1) {
                ChapterWrap next = chapter.getNext(apiServices);
                if (next == null) {
                    return null;
                }
                List<VerseWrap> nextVerses = next.getVerses(apiServices.getVersesService());
                return nextVerses.get(0).stepVerses(--steps, apiServices);
            } else {
                return chapter.getVerses(apiServices.getVersesService()).get(myIndex + 1).stepVerses(--steps, apiServices);
            }
        }
        return null;
    }

    public int diffVerses(VerseWrap to, ApiServices apiServices) throws ApiException {
        int diff = 0;
        if (this.getChapter() == to.getChapter()) {
            diff += Math.abs(getIndex(apiServices) - to.getIndex(apiServices));
        } else {
            diff += ChapterWrap.diffVerses(this, to, apiServices);
        }
        return diff;
    }

    public String getText() {
        return verseSummary.getReference();
    }
}
