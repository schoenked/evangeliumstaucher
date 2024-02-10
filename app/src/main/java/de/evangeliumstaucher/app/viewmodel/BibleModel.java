package de.evangeliumstaucher.app.viewmodel;

import de.evangeliumstaucher.model.BibleSummary;
import lombok.*;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BibleModel extends BaseModel {
    private String url;
    private String id;
    private String label;
    private String language;

    public static BibleModel from(BibleSummary bibleSummary) {
        BibleModelBuilder builder = BibleModel.builder()
                .id(bibleSummary.getId())
                .url(bibleSummary.getId() + "/")
                .language(bibleSummary.getLanguage().getNameLocal())
                .label(bibleSummary.getAbbreviationLocal() +
                        " - " + bibleSummary.getNameLocal() +
                        " - " + bibleSummary.getDescriptionLocal());
        return builder.build();
    }
}
