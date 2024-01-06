package com.devrezaur.main.viewmodel;

import com.devrezaur.main.model.VerseWrap;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizModel {
    private String id;
    private List<VerseWrap> verses = new ArrayList<>();
}
