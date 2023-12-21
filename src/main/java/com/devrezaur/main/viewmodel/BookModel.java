package com.devrezaur.main.viewmodel;

import de.evangeliumstaucher.model.Book;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@With(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class BookModel {

    private Collection<ChapterModel> chapters;
    private String id;
    private String abbreviation;

    public static List<BookModel> from(List<Book> books) {

        return books.stream()
                .map(BookModel::from)
                .collect(Collectors.toList());
    }

    public static BookModel from(Book book) {
        return new BookModel()
                .withChapters(ChapterModel.from(book.getChapters()))
                .withAbbreviation(book.getAbbreviation())
                .withId(book.getId());
    }
}
