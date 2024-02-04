package de.evangliumstaucher.app.viewmodel;

import de.evangliumstaucher.app.model.BibleWrap;
import de.evangliumstaucher.app.model.VerseWrap;
import de.evangeliumstaucher.entity.GameEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class QuizModel {
    private final BibleWrap bible;
    private String id;
    private List<VerseWrap> verses = new ArrayList<>();

    public List<VerseWrap> getVerses() {
        if (verses == null) {
            verses = new ArrayList<>();
        }
        return verses;
    }

    public String getUrl() {
        return "/quiz/" + getId() + "/";
    }

    public GameEntity getEntity() {
        return new GameEntity(null,"","",null, null);
    }
}