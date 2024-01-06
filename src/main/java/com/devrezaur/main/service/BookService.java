package com.devrezaur.main.service;

import com.devrezaur.main.model.BibleWrap;
import com.devrezaur.main.model.BookWrap;
import de.evangeliumstaucher.BooksApi;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BooksApi booksApi;
    private final VersesService versesService;
    HashMap<String, BibleWrap> biblesCache = new HashMap<>();

    public BibleWrap getBible(String bibleId) throws ApiException {
        if (biblesCache.containsKey(bibleId)) {
            return biblesCache.get(bibleId);
        }
        List<Book> books = getBibleBooks(bibleId);
        BibleWrap bibleWrapped = new BibleWrap();
        bibleWrapped.setBooks(books.stream()
                .map(book ->
                        new BookWrap(book, bibleWrapped))
                .collect(Collectors.toList()));
        biblesCache.put(bibleId, bibleWrapped);
        return bibleWrapped;
    }

    public List<Book> getBibleBooks(String bibleId) throws ApiException {
        return booksApi.getBooks(bibleId, true, false).getData();
    }
}
