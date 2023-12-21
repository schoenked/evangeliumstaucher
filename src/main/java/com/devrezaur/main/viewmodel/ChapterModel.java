package com.devrezaur.main.viewmodel;

import de.evangeliumstaucher.model.ChapterSummary;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Data
@With(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ChapterModel {

    private String label;

    public static Collection<ChapterModel> from(List<ChapterSummary> chapters) {
        return chapters.stream()
                .map(ChapterModel::from)
                .collect(Collectors.toList());
    }

    public static ChapterModel from(ChapterSummary chapter) {
        return new ChapterModel()
                .withLabel(chapter.getNumber());
    }
}