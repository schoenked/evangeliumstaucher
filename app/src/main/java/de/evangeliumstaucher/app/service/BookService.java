package de.evangeliumstaucher.app.service;

import de.evangeliumstaucher.BooksApi;
import de.evangeliumstaucher.app.model.BibleWrap;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BooksApi booksApi;
    private final VersesService versesService;
    private final BibleService bibleService;

    public BibleWrap getBible(String bibleId) throws ApiException {
        return bibleService.get(bibleId);
    }

    @Cacheable("bibleBooks")
    public List<Book> getBibleBooks(String bibleId) throws ApiException {
        return booksApi.getBooks(bibleId, true, false).getData();
    }
}
