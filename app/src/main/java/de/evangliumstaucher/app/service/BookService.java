package de.evangliumstaucher.app.service;

import de.evangeliumstaucher.BooksApi;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Book;
import de.evangliumstaucher.app.model.BibleWrap;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BooksApi booksApi;
    private final VersesService versesService;
    private final BibleService bibleService;

    public BibleWrap getBible(String bibleId) throws ApiException {
        return bibleService.get(bibleId);
    }

    @Cacheable
    public List<Book> getBibleBooks(String bibleId) throws ApiException {
        return booksApi.getBooks(bibleId, true, false).getData();
    }
}
