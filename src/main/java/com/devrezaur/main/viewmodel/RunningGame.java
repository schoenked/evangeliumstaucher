package com.devrezaur.main.viewmodel;

import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RunningGame {
    private List<RunningQuestion> questions = new LinkedList<>();
    private QuizModel quizModel;
}
