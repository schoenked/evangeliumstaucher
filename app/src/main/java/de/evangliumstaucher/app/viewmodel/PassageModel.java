package de.evangliumstaucher.app.viewmodel;

import de.evangeliumstaucher.model.Passage;
import lombok.*;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PassageModel extends BaseModel {
    private String content;
    private String htmlClasses;
    private String htmlId;
    private String path;
    private PassageLoader passageLoader;

    public PassageModel(String path, PassageLoader passageLoader) {
        this.path = path;
        this.passageLoader = passageLoader;
    }

    public static PassageModel from(Passage passage) {
        PassageModelBuilder builder = PassageModel.builder();
        if (passage != null) {
            builder.content(passage.getContent());
        }
        return builder.build();
    }

    @Data
    @With
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PassageLoader {
        private int delay = 5000;
    }
}
