package com.devrezaur.main.viewmodel;

import de.evangeliumstaucher.model.Passage;
import lombok.*;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassageModel extends BaseModel {
    private String content;

    public static PassageModel from(Passage passage) {
        return PassageModel.builder()
                .content(passage.getContent())
                .build();
    }
}