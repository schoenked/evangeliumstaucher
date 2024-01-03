package com.devrezaur.main.viewmodel;

import de.evangeliumstaucher.model.VerseSummary;
import lombok.*;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizModel {
    private VerseSummary verse;
}
