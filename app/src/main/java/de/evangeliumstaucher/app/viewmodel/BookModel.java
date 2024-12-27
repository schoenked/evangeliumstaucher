package de.evangeliumstaucher.app.viewmodel;

import de.evangeliumstaucher.app.utils.ListUtils;
import de.evangeliumstaucher.repo.model.BibleBook;
import de.evangeliumstaucher.repo.model.Division;
import de.evangeliumstaucher.repo.service.Library;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@With
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookModel extends BaseModel {

    private List<ChapterModel> chapters;
    private String abbreviation;
    private String prefixVerses;
    private String color;

    public static List<BookModel> from(List<? extends BibleBook> books, String prefixVerse, Library library) {

        List<BookModel> list = new ArrayList<>();
        for (BibleBook book : books) {
            BookModel from = from(book, prefixVerse, library);
            list.add(from);
        }
        return list;
    }

    public static BookModel from(BibleBook book, String prefixVerse, Library library) {
        BookModel bookModel = BookModel.builder()
                .chapters(ChapterModel.from(book.getChapters(), library))
                .abbreviation(book.getAbbreviation())
                .build();
        bookModel.setId(book.getId());
        bookModel.setPrefixVerses(prefixVerse);
        String color = "black";
        Optional<Division> smallest = book.getDivisions().stream().min((o1, o2) -> Integer.compare(o1.getSize(), o2.getSize()));
        if (smallest.isPresent()) {
            color = smallest.get().getColor();
        }
        bookModel.setColor(color);
        return bookModel;
    }

    public String getAbbreviationShort() {
        return getAbbreviation().replace(" ", "");
    }

    public List<List<ChapterModel>> chapterGroups() {
        return ListUtils.groupsOf(chapters, 5);
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

}
