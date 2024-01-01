package com.devrezaur.main.viewmodel;

import com.devrezaur.main.service.VersesService;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Book;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@With
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookModel extends BaseModel {

    @Override
    public String getId() {
        return super.getId();
    }

    private Collection<ChapterModel> chapters;
    private String abbreviation;

    public static List<BookModel> from(List<Book> books, VersesService versesService) throws ApiException {

        List<BookModel> list = new ArrayList<>();
        for (Book book : books) {
            BookModel from = from(book, versesService);
            list.add(from);
        }
        return list;
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    public static BookModel from(Book book, VersesService versesService) throws ApiException {
        BookModel bookModel = BookModel.builder()
                .chapters(ChapterModel.from(book.getChapters(), versesService))
                .abbreviation(book.getAbbreviation())
                .build();
        bookModel.setId(book.getId());
        return bookModel;
    }

}
