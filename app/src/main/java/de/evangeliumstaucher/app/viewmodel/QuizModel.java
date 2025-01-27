package de.evangeliumstaucher.app.viewmodel;

import de.evangeliumstaucher.app.model.BibleWrap;
import de.evangeliumstaucher.app.service.QuizService;
import de.evangeliumstaucher.entity.GameEntity;
import de.evangeliumstaucher.entity.PlayerEntity;
import de.evangeliumstaucher.repo.model.Verse;
import de.evangeliumstaucher.repo.service.Library;
import jakarta.validation.constraints.NotNull;
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
    private List<String> tags;
    private int distanceRatingFactor;
    private int timeRatingFactor;
    private String whitelist;
    private String blacklist;


    public static QuizModel from(GameEntity gameEntity, Library library) {
        return QuizModel.builder()
                .id(gameEntity.getId())
                .bibleId(gameEntity.getBibleId())
                .name(gameEntity.getName())
                .description(gameEntity.getDescription())
                .tags(gameEntity.getTags())
                .distanceRatingFactor(gameEntity.getDistanceRatingFactor())
                .timeRatingFactor(gameEntity.getTimeRatingFactor())
                .build();
    }

    public BibleWrap getBible(Library library) {
        if (bible == null) {
            bible = new BibleWrap(bibleId, library.getBible(bibleId));
        }
        return bible;
    }

    public @NotNull List<Verse> createVerses(QuizService quizService, int countVerses, String whitelist, String blacklist) {
        if (verses == null) {
            verses = new ArrayList<>();
        }
        //prvent verse loops
        StringBuilder previousVerses = new StringBuilder();
        for (int i = 0; i < countVerses; i++) {
            Verse q = quizService.getQuestionVerse(this, whitelist, blacklist + previousVerses);
            if (q == null) {
                //reset extension of blacklist
                previousVerses = new StringBuilder();
                q = quizService.getQuestionVerse(this, whitelist, blacklist);
            }
            if (q != null) {
                //add the verse to blacklist
                previousVerses.append(", ").append(q.getId());
            }
            if (q != null) {
                verses.add(q);
            }
        }
        return verses;
    }

    public String getUrl() {
        return "/quiz/" + getId() + "/";
    }

    public String getStartUrl() {
        return getUrl() + "next";
    }

    public GameEntity getEntity() {
        return new GameEntity(getId(),
                name,
                description,
                bibleId,
                new PlayerEntity().withId(creator.getId()),
                null,
                tags,
                distanceRatingFactor,
                timeRatingFactor,
                whitelist,
                blacklist
        );
    }

}
