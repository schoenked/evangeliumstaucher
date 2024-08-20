package de.evangeliumstaucher.app.model;

import de.evangeliumstaucher.repo.model.BibleBook;
import de.evangeliumstaucher.repo.service.Library;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class BibleWrap {
    private final String id;
    @Getter(AccessLevel.PRIVATE)
    private List<BookWrap> books;

    public List<BookWrap> getBooks(Library library)   {
        if (books == null) {
            List<BibleBook> booklist = library.getBibleBooks(id);
            books = booklist.stream()
                    .map(book ->
                            new BookWrap(book, this))
                    .collect(Collectors.toList());
        }
        return books;
    }
/*    @ToString.Exclude
    private final Bible bible;*/

    public BookWrap getLast(Library library) {
        return getBooks().get(getBooks().size() - 1);
    }
}
