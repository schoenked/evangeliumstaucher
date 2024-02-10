package de.evangeliumstaucher.app.model;

import de.evangeliumstaucher.app.service.BookService;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Book;
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
    private List<BookWrap> books;

    public List<BookWrap> getBooks(BookService bookService) throws ApiException {
        if (books == null) {
            List<Book> booklist = bookService.getBibleBooks(id);
            books = booklist.stream()
                    .map(book ->
                            new BookWrap(book, this))
                    .collect(Collectors.toList());
        }
        return books;
    }
/*    @ToString.Exclude
    private final Bible bible;*/

    public BookWrap getLast() {
        return books.get(books.size() - 1);
    }
}
