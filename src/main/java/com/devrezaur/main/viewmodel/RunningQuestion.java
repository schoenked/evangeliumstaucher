package com.devrezaur.main.viewmodel;

import com.devrezaur.main.model.VerseWrap;
import lombok.*;

import java.util.Date;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RunningQuestion {
    private Date startedAt = new Date();
    int extendingPrePassageCount = 0;
    int extendingPostPassageCount = 0;
    private VerseWrap verse;
    private VerseWrap precontextVerse;
    private VerseWrap postcontextVerse;
}
