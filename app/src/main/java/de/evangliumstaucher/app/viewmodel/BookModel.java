package de.evangliumstaucher.app.viewmodel;

import de.evangliumstaucher.app.service.VersesService;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Book;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@With
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookModel extends BaseModel {

    private Collection<ChapterModel> chapters;
    private String abbreviation;
    private String prefixVerses;

    public static List<BookModel> from(List<Book> books, VersesService versesService) throws ApiException {

        List<BookModel> list = new ArrayList<>();
        for (Book book : books) {
            BookModel from = from(book, versesService);
            list.add(from);
        }
        return list;
    }

    public static BookModel from(Book book, VersesService versesService) throws ApiException {
        BookModel bookModel = BookModel.builder()
                .chapters(ChapterModel.from(book.getChapters(), versesService))
                .abbreviation(book.getAbbreviation())
                .build();
        bookModel.setId(book.getId());
        return bookModel;
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
