package de.evangeliumstaucher.sword;

import org.crosswire.jsword.book.Book;
import org.crosswire.jsword.book.BooksEvent;
import org.crosswire.jsword.book.BooksListener;

import java.util.concurrent.locks.Lock;

public class SwordBooksListener implements BooksListener {
    private final Lock lock;
    Book installedBook;

    public SwordBooksListener(Lock lock) {
        this.lock = lock;
    }

    public Book getInstalledBook() {
        return installedBook;
    }

    @Override
    public void bookAdded(BooksEvent booksEvent) {
        //return book to requesting function
        this.installedBook = booksEvent.getBook();
        lock.unlock();
    }

    @Override
    public void bookRemoved(BooksEvent booksEvent) {

    }
}
