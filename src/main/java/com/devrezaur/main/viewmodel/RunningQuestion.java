package com.devrezaur.main.viewmodel;

import com.devrezaur.main.model.VerseWrap;
import com.devrezaur.main.service.VersesService;
import de.evangeliumstaucher.invoker.ApiException;
import lombok.*;

import java.util.Date;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RunningQuestion {
    int extendingPrePassageCount = 0;
    int extendingPostPassageCount = 0;
    private Date startedAt = new Date();
    private Date answeredAt;
    private VerseWrap verse;
    private VerseWrap selectedVerse;
    private VerseWrap contextStartVerse;
    private VerseWrap contextEndVerse;

    public int diffVerses(VersesService versesService) throws ApiException {

        return verse.diffVerses(selectedVerse, versesService);
    }
}
