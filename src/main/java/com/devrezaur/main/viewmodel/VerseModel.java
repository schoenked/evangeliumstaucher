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
        return new VerseModel()
                .withId(verseSummary.getId());
    }
}
