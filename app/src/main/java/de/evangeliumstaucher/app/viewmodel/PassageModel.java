package de.evangeliumstaucher.app.viewmodel;

import de.evangeliumstaucher.repo.model.Passage;
import lombok.*;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class PassageModel extends BaseModel {
    private String content;
    private String htmlClasses;
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

    public int getHashcode() {
        return hashCode();
    }

    @Override
    public String getHtmlIdEscaped() {
        return super.getHtmlIdEscaped();
    }

    @Data
    @With
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PassageLoader {
        private int delay = 5000;
    }
}
