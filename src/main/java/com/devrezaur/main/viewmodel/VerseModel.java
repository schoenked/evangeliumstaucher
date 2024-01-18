package com.devrezaur.main.viewmodel;

import de.evangeliumstaucher.model.VerseSummary;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

@Data
@With(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class VerseModel {
    //  public static final Pattern PATTERN = Pattern.compile("\\d+$");
    private String id;
    private String url;
    private String label;

    public static VerseModel from(VerseSummary verseSummary) {
        String versenr = verseSummary.getOrgId();
        versenr = StringUtils.substringAfterLast(versenr, ".");

        return new VerseModel()
                .withId(verseSummary.getId())
                .withLabel(versenr)
                .withUrl(verseSummary.getId());
    }
}
