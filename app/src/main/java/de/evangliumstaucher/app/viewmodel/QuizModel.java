package de.evangliumstaucher.app.viewmodel;

import de.evangeliumstaucher.entity.GameEntity;
import de.evangliumstaucher.app.model.BibleWrap;
import de.evangliumstaucher.app.model.VerseWrap;
import de.evangliumstaucher.app.service.BibleService;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class QuizModel {
    private final String bibleId;
    private final UUID id;
    private String name;
    private String player;
    private String description;
    private Date createdAt;
    private BibleWrap bible;
    private List<VerseWrap> verses = new ArrayList<>();

    public static QuizModel from(GameEntity gameEntity, BibleService bibleService) {
        return QuizModel.builder()
                .id(gameEntity.getId())
                .bibleId(gameEntity.getBibleId())
                .build();
    }

    public BibleWrap getBible(BibleService bibleService) {
        if (bible == null) {
            bible = bibleService.get(bibleId);
        }
        return bible;
    }

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
        return new GameEntity(getId(),
                name,
                description,
                bibleId,
                player,
                null);
    }
}
