package de.evangliumstaucher.app.viewmodel;

import de.evangliumstaucher.app.service.VersesService;
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
    private String bibleId;

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
                .withBibleId(chapter.getBibleId())
                .withLabel(chapter.getNumber());
        chapterModel.setId(chapter.getId());
        return chapterModel;
    }
}