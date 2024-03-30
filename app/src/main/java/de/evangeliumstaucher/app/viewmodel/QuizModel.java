package de.evangeliumstaucher.app.viewmodel;

import de.evangeliumstaucher.app.model.BibleWrap;
import de.evangeliumstaucher.app.model.VerseWrap;
import de.evangeliumstaucher.app.service.BibleService;
import de.evangeliumstaucher.app.service.QuizService;
import de.evangeliumstaucher.entity.GameEntity;
import de.evangeliumstaucher.entity.PlayerEntity;
import de.evangeliumstaucher.invoker.ApiException;
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
    private static final int QUESTION_COUNT = 10;
    private final String bibleId;
    private final PlayerModel creator;
    private UUID id;
    private String name;
    private String description;
    private Date createdAt;
    @Getter(AccessLevel.PRIVATE)
    private BibleWrap bible;
    @Getter(AccessLevel.PRIVATE)
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

    public List<VerseWrap> getVerses(QuizService quizService) throws ApiException {
        if (verses == null) {
            verses = new ArrayList<>();
            for (int i = 0; i < QUESTION_COUNT; i++) {
                VerseWrap q = quizService.getQuestionVerse(this);
                verses.add(q);
            }
        }
        return verses;
    }

    public String getUrl() {
        return "/quiz/" + getId() + "/";
    }

    public String getStartUrl() {
        return getUrl() + "0/";
    }

    public GameEntity getEntity() {
        return new GameEntity(getId(),
                name,
                description,
                bibleId,
                new PlayerEntity().withId(creator.getId()),
                null);
    }
}
