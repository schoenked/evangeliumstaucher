package de.evangeliumstaucher.sword;

import org.crosswire.jsword.book.BookData;
import org.crosswire.jsword.book.BookException;
import org.crosswire.jsword.book.Books;
import org.crosswire.jsword.book.OSISUtil;
import org.crosswire.jsword.book.install.InstallException;
import org.crosswire.jsword.book.install.InstallManager;
import org.crosswire.jsword.book.install.Installer;
import org.crosswire.jsword.book.sword.SwordBook;
import org.crosswire.jsword.passage.Key;
import org.crosswire.jsword.passage.NoSuchKeyException;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Semaphore;

import static com.google.common.truth.Truth.assertThat;

public class SwordTest {

    @Test
    public void testLoadModule() throws NoSuchKeyException, BookException, InstallException, InterruptedException {
        InstallManager imanager = new InstallManager();
        Installer i = imanager.getInstaller("CrossWire");
        List<SwordBook> books = i.getBooks()
                .stream()
                .filter(BibleService::filterBibles)
                .filter(b -> b instanceof SwordBook)
                .map(book -> (SwordBook) book)
                .filter(swordBook -> {
                    return swordBook.getBookMetaData().getName().equals("Neue evangelistische Übersetzung");
                })
                .toList();
        SwordBook book = books.get(0);
        i.install(book);
        book = (SwordBook) Books.installed().getBook(book.getName());
      /*  for (int j = 0; j < 1000000; j++) {
            Verse verse = new Verse(book.getVersification(),
                    j);
            BookData bookData = new BookData(book, verse);
            String canonicalText = OSISUtil.getCanonicalText(bookData.getOsisFragment());
            String rawText = book.getRawText(verse);
            System.out.println(verse.getName() + ":"+ canonicalText);
            //Thread.sleep(1);
        }*/
        Key key = book.getKey("Joh 3:16");
        BookData bookData = new BookData(book, key);

        String canonicalText = OSISUtil.getCanonicalText(bookData.getOsisFragment());
        assertThat(canonicalText).contains("so hat Gott der Welt seine Liebe gezeigt");
    }

    @Test
    public void testSemaphore() {
        Semaphore s = new Semaphore(1);
        try {
            s.acquire();
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                s.release();
            }).start();
            s.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testCopyright() {
        new InstallManager().getInstaller("CrossWire")
                .getBooks()
                .stream()
                .filter(b -> b instanceof SwordBook)
                .filter(BibleService::filterBibles)
                .map(book -> (SwordBook) book)
                .map(swordBook -> BibleService.toBible(swordBook))
                .map(bible -> {
                    System.out.println("testCopyright " + bible.getCopyright());
                    return  bible;
                }).toList();
                //.allMatch(bible -> bible.getCopyright().length() > 2);
    }
}
