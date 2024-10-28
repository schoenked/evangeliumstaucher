package de.evangeliumstaucher.app.viewmodel;

import de.evangeliumstaucher.app.model.BibleWrap;
import de.evangeliumstaucher.app.service.QuizService;
import de.evangeliumstaucher.entity.GameEntity;
import de.evangeliumstaucher.entity.PlayerEntity;
import de.evangeliumstaucher.repo.model.Verse;
import de.evangeliumstaucher.repo.service.Library;
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
    private final PlayerModel creator;
    private UUID id;
    private String name;
    private String description;
    private Date createdAt;
    @Getter(AccessLevel.PRIVATE)
    private BibleWrap bible;
    private List<Verse> verses = new ArrayList<>();

    public static QuizModel from(GameEntity gameEntity, Library library) {
        return QuizModel.builder()
                .id(gameEntity.getId())
                .bibleId(gameEntity.getBibleId())
                .build();
    }

    public BibleWrap getBible(Library library) {
        if (bible == null) {
            bible = new BibleWrap(bibleId, library.getBible(bibleId));
        }
        return bible;
    }

    public List<Verse> createVerses(QuizService quizService, int countVerses) {
        if (verses == null) {
            verses = new ArrayList<>();
        }
        for (int i = 0; i < countVerses; i++) {
            Verse q = quizService.getQuestionVerse(this);
            verses.add(q);
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
