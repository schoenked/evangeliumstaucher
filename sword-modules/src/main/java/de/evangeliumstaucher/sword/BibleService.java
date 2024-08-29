package de.evangeliumstaucher.sword;

import de.evangeliumstaucher.repo.model.Bible;
import de.evangeliumstaucher.sword.model.SwordBible;
import lombok.extern.slf4j.Slf4j;
import org.crosswire.jsword.book.Book;
import org.crosswire.jsword.book.BookCategory;
import org.crosswire.jsword.book.Books;
import org.crosswire.jsword.book.install.InstallManager;
import org.crosswire.jsword.book.install.Installer;
import org.crosswire.jsword.book.sword.SwordBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
public class BibleService {
    private static final String PREFIX = "sword-";
    @Autowired
    public AsyncInstaller asyncInstaller;
    InstallManager imanager = new InstallManager();

    public Bible getBible(String name) {
        log.trace("loading bible {}", name);
        if (name.startsWith(PREFIX)) {
            //trim prefix
            name = name.substring(PREFIX.length());
            String swordName = decodeName(name);
            log.trace("bible name {}", swordName);
            Book book = Books.installed().getBook(swordName);

            if (book == null) {
                log.trace("bible not installed yet");
                book = install(swordName);
            }
            return toBible(book);
        }
        return null;
    }

    private boolean filterBibles(Book book) {
        try {
            //error in sword lib
            if (book.getBookCategory() != null && book.getBookCategory() == BookCategory.BIBLE) {
                return true;
            }
        } catch (Exception _) {
        }
        return false;
    }

    private Bible toBible(@Nullable Book book) {
        if (book instanceof SwordBook swordBook) {

            SwordBible swordBible = SwordBible
                    .builder()
                    .swordBook(swordBook)
                    .id(PREFIX + encodeName(book.getName()))
                    .name(book.getName())
                    .description(book.getProperties().getOrDefault("desc", "").toString())
                    .abbreviation(book.getInitials())
                    .language(book.getLanguage() != null ? book.getLanguage().getName() : "N/A")
                    .build();
            return swordBible;
        }
        return null;
    }

    private String decodeName(String name) {
        return new String(Base64.getDecoder().decode(name));
    }

    private String encodeName(String coded) {
        return Base64.getEncoder().encodeToString(coded.getBytes());
    }

    @Nullable
    private Book install(String name) {
        SwordBooksListener listener = getListener();
        Books.installed().addBooksListener(listener);
        for (Installer installer : imanager.getInstallers().values()) {
            Book book = installer.getBook(name);

            if (book != null) {
                try {
                    //wait until installed and return installed book
                    log.info("installing bible {}", name);
                    asyncInstaller.installAsync(installer, book);

                    //waits until installed
                    listener.getLock().acquire();

                    log.info("bible {} installed", name);
                    Book installedBook = listener.getInstalledBook();
                    Books.installed().removeBooksListener(listener);
                    return installedBook;
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
                log.error("Book {} not installed", name);
            }
        }
        log.warn("installation of Book {} failed", name);
        Books.installed().removeBooksListener(listener);
        return null;
    }


    private SwordBooksListener getListener() {
        return new SwordBooksListener();
    }

    public List<Bible> getBibles() {
        log.trace("getting all bibles");
        return imanager.getInstallers()
                .values()
                .stream()
                .flatMap(this::getBibles)
                .toList();
    }

    private Stream<Bible> getBibles(Installer installer) {
        Stream<Bible> bibleStream = null;
        try {
            List<Book> books = installer.getBooks();
            bibleStream = books
                    .stream()
                    .filter(this::filterBibles)
                    .map(this::toBible);
        } catch (Exception e) {
            //   log.error(e.getMessage(), e);
        }
        return bibleStream;
    }
}