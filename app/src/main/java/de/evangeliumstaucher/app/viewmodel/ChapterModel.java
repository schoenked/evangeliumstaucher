package de.evangeliumstaucher.app.viewmodel;

import de.evangeliumstaucher.repo.model.Chapter;
import de.evangeliumstaucher.repo.model.Verse;
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
    private VerseModel firstVerse;

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

        Verse firstVerse = library.getVerses(chapter.getBibleId(), chapter.getId()).getFirst();
        chapterModel.setFirstVerse(VerseModel.from(firstVerse));

        chapterModel.setId(chapter.getId());
        return chapterModel;
    }

}