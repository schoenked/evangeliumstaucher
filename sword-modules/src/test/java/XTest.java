import org.crosswire.jsword.book.Book;
import org.crosswire.jsword.book.BookData;
import org.crosswire.jsword.book.BookFilters;
import org.crosswire.jsword.book.install.InstallManager;
import org.crosswire.jsword.book.install.Installer;
import org.crosswire.jsword.passage.NoSuchKeyException;
import org.junit.Test;

import java.util.List;

public class XTest {

    @Test
    public void testLoaqdModule() throws NoSuchKeyException {
        InstallManager imanager = new InstallManager();
        Installer i = imanager.getInstaller("CrossWire");
        List<Book> books = i.getBooks(BookFilters.getBibles());
        Book book = books.get(0);
        book.
                BookData data = new BookData(book, books.get(0).getKey(""));
    }
}
