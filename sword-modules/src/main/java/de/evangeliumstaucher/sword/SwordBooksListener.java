package de.evangeliumstaucher.sword;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.crosswire.jsword.book.Book;
import org.crosswire.jsword.book.BooksEvent;
import org.crosswire.jsword.book.BooksListener;

import java.util.concurrent.Semaphore;

@Getter
@Slf4j
public class SwordBooksListener implements BooksListener {
    private final Semaphore lock;
    Book installedBook;

    public SwordBooksListener() {
        this.lock = new Semaphore(1);
        this.lock.acquireUninterruptibly();
    }

    @Override
    public void bookAdded(BooksEvent booksEvent) {
        log.info("Book added: {}", booksEvent);
        //return book to requesting function
        this.installedBook = booksEvent.getBook();
        lock.release();
    }

    @Override
    public void bookRemoved(BooksEvent booksEvent) {

    }
}
