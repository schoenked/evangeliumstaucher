package de.evangeliumstaucher.app.viewmodel;

import de.evangeliumstaucher.repo.model.Bible;
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

    public static BibleModel from(Bible bibleSummary) {
        BibleModelBuilder builder = BibleModel.builder()
                .id(bibleSummary.getId())
                .url(bibleSummary.getId() + "/")
                .language(bibleSummary.getLanguage())
                .label(bibleSummary.getAbbreviation() +
                        " - " + bibleSummary.getName() +
                        " - " + bibleSummary.getDescription());
        return builder.build();
    }
}
