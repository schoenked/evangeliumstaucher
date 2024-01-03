package com.devrezaur.main.service;

import de.evangeliumstaucher.BooksApi;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BooksApi booksApi;

    public List<Book> getBibleBooks(String bibleId) throws ApiException {
        return booksApi.getBooks(bibleId, true, false).getData();
    }
}
