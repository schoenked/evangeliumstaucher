package com.devrezaur.main.viewmodel;

import de.evangeliumstaucher.model.VerseSummary;
import lombok.*;

@Data
@With(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class VerseModel {
    private String id;
    private String url;

    public String getLabel() {
        return id;
    }

    public static VerseModel from(VerseSummary verseSummary) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new VerseModel()
                .withId(verseSummary.getId());
    }
}
