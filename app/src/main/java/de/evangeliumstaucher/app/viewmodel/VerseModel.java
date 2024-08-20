package de.evangeliumstaucher.app.viewmodel;

import de.evangeliumstaucher.repo.model.Verse;
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

    public static VerseModel from(Verse verse) {
        String versenr = verse.getId();
        versenr = StringUtils.substringAfterLast(versenr, ".");

        return new VerseModel()
                .withId(verse.getId())
                .withLabel(versenr)
                .withUrl(verse.getId());
    }
}
