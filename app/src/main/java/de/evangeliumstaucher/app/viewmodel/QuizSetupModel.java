package de.evangeliumstaucher.app.viewmodel;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import de.evangeliumstaucher.app.model.BibleWrap;
import de.evangeliumstaucher.app.model.PassageTree;
import de.evangeliumstaucher.repo.service.Library;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class QuizSetupModel {
    @NotEmpty
    private String name;      // Name of the quiz

    @Min(0)
    @Max(500)
    private int timeAttribute = 100;        // Percentage for time attribute (0 to 500)

    @Min(0)
    @Max(500)
    private int distanceAttribute = 100;    // Percentage for distance attribute (0 to 500)

    @Size(max = 2000)
    private String description;       // Additional description

    @Min(1)
    @Max(100)
    private int countVerses = 10;

    @Size(max = 10000000)

    private List<String> passages;

    @Size(max = 500)
    private String tags;
    private PassageTree passageTree;

    public Map<String,Object> unknownProperties = new HashMap<>();

    public static QuizSetupModel from(BibleWrap bibleWrap, Library library) {
        QuizSetupModel quizSetupModel = new QuizSetupModel();
        quizSetupModel.setPassageTree(bibleWrap.getPassageTree(library));
        return quizSetupModel;
    }

    // Method to capture unknown properties
    @JsonAnySetter
    public void setUnknownProperty(String key, Object value) {
        unknownProperties.put(key, value);
    }
}



