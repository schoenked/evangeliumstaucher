package com.devrezaur.main.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class ResultModel {

    private String timespan;
    private int verseDiff;
    private int points;
    private int pointsSum;
    private String urlNext;
    private String searchedVerse;
    private String selectedVerse;
}
