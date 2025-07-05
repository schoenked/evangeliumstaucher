package de.evangeliumstaucher.app.viewmodel;

import de.evangeliumstaucher.repo.model.Bible;
import lombok.*;
import org.apache.commons.lang3.RandomUtils;

@Data
@With
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BibleModel extends BaseModel {
    private String url;
    private String id;
    private String label;
    private String language;
    private String languageCode;

    public static BibleModel from(Bible bibleSummary) {
        RandomUtils.nextInt(0, 100);
        BibleModelBuilder builder = BibleModel.builder()
                .id(bibleSummary.getId())
                .url(bibleSummary.getId() + "/")
                .language(bibleSummary.getLanguage())
                .languageCode(bibleSummary.getLanguageCode())
                .label(bibleSummary.getName());
        return builder.build();
    }

}
