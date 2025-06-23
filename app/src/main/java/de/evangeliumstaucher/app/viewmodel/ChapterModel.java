package de.evangeliumstaucher.app.viewmodel;

import de.evangeliumstaucher.repo.model.Chapter;
import de.evangeliumstaucher.repo.service.Library;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@With(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ChapterModel extends BaseModel {

    private String label;
    private List<VerseModel> verses;
    private String bibleId;

    public static List<ChapterModel> from(Collection<Chapter> chapters, Library library) {
        List<ChapterModel> list = new ArrayList<>();
        for (Chapter chapter : chapters) {
            ChapterModel model = from(chapter, library);
            list.add(model);
        }
        return list;
    }

    public static ChapterModel from(Chapter chapter, Library library) {
        ChapterModel chapterModel = new ChapterModel()
                .withBibleId(chapter.getBibleId())
                .withLabel(chapter.getNumber());
        chapterModel.setId(chapter.getId());
        return chapterModel;
    }
}