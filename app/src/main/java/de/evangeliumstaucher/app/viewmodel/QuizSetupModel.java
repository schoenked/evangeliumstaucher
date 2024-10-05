package de.evangeliumstaucher.app.viewmodel;

import lombok.Data;

import java.util.List;

@Data
public class QuizSetupModel {
    private String name;              // Name of the quiz
    private int timeAttribute = 100;        // Percentage for time attribute (0 to 500)
    private int distanceAttribute = 100;    // Percentage for distance attribute (0 to 500)
    private String description;       // Additional description
    private List<String> passages;
    private String tags;

}
