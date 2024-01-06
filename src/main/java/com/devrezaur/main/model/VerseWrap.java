package com.devrezaur.main.model;

import de.evangeliumstaucher.model.VerseSummary;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class VerseWrap {
    private final ChapterWrap chapter;
    private final VerseSummary verseSummary;

    public VerseWrap stepVerses(int steps) {

        return null;
    }
}
