package com.devrezaur.main.viewmodel;

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
    private String path;
    private PassageLoader passageLoader;

    public static PassageModel from(Passage passage) {
        return PassageModel.builder()
                .content(passage.getContent())
                .build();
    }

    @Data
    @With
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PassageLoader {
        private int delay = 5000;
    }
}
