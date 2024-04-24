package de.evangeliumstaucher.app.viewmodel;

import de.evangeliumstaucher.app.service.VersesService;
import de.evangeliumstaucher.app.utils.ListUtils;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Book;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@With
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookModel extends BaseModel {

    private List<ChapterModel> chapters;
    private String abbreviation;
    private String prefixVerses;

    public static List<BookModel> from(List<Book> books, String prefixVerse, VersesService versesService) throws ApiException {

        List<BookModel> list = new ArrayList<>();
        for (Book book : books) {
            BookModel from = from(book, prefixVerse, versesService);
            list.add(from);
        }
        return list;
    }

    public String getAbbreviationShort() {
        return getAbbreviation().replace(" ", "");
    }

    public static BookModel from(Book book, String prefixVerse, VersesService versesService) throws ApiException {
        BookModel bookModel = BookModel.builder()
                .chapters(ChapterModel.from(book.getChapters(), versesService))
                .abbreviation(book.getAbbreviation())
                .build();
        bookModel.setId(book.getId());
        bookModel.setPrefixVerses(prefixVerse);
        return bookModel;
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
