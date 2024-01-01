package com.devrezaur.main.viewmodel;

import com.devrezaur.main.service.VersesService;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.ChapterSummary;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@With(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ChapterModel extends BaseModel {

    private String label;
    private List<VerseModel> verses;

    public static Collection<ChapterModel> from(List<ChapterSummary> chapters, VersesService versesService) throws ApiException {
        List<ChapterModel> list = new ArrayList<>();
        for (ChapterSummary chapter : chapters) {
            ChapterModel model = from(chapter, versesService);
            list.add(model);
        }
        return list;
    }

    public static ChapterModel from(ChapterSummary chapter, VersesService versesService) throws ApiException {
        ChapterModel chapterModel = new ChapterModel()
                .withLabel(chapter.getNumber());
        chapterModel.setId(chapter.getId());
        return chapterModel;
    }
}