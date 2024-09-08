package de.evangeliumstaucher.sword;

import com.google.common.collect.Lists;
import com.google.common.collect.Streams;
import de.evangeliumstaucher.repo.model.Bible;
import de.evangeliumstaucher.repo.model.BibleBook;
import de.evangeliumstaucher.repo.model.Passage;
import de.evangeliumstaucher.repo.model.Verse;
import de.evangeliumstaucher.repo.service.Library;
import de.evangeliumstaucher.sword.model.SwordBible;
import de.evangeliumstaucher.sword.model.SwordBibleBook;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.crosswire.jsword.book.BookData;
import org.crosswire.jsword.book.BookException;
import org.crosswire.jsword.book.OSISUtil;
import org.crosswire.jsword.book.sword.SwordBook;
import org.crosswire.jsword.passage.Key;
import org.crosswire.jsword.passage.NoSuchKeyException;
import org.crosswire.jsword.passage.NoSuchVerseException;
import org.crosswire.jsword.passage.PassageTally;
import org.crosswire.jsword.versification.BookName;
import org.crosswire.jsword.versification.Versification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SwordLibrary implements Library {

    private final BibleService bibleService;

    @Override
    public Bible getBible(String name) {
        return bibleService.getBible(name);
    }

    @Override
    public List<? extends BibleBook> getBibleBooks(String bibleName) {
        return getBibleBooks(getBible(bibleName));
    }

    @Override
    public List<Bible> getBibles() {
        return bibleService.getBibles();
    }

    @Override
    public Passage getPassage(String bibleId, String passageId) {
        Bible bible = getBible(bibleId);

        if (bible instanceof SwordBible swordBible) {
            Versification versification = swordBible.getSwordBook().getVersification();

            try {
                Key key = ((SwordBible) bible).getSwordBook().getKey(passageId);
                BookData bookData = new BookData(swordBible.getSwordBook(), key);
                String canonicalText = OSISUtil.getCanonicalText(bookData.getOsisFragment());
                SwordPassage passage = new SwordPassage();
                passage.setId(passageId);
                passage.setContent(canonicalText);
                return passage;
            } catch (BookException e) {
                log.error(e.getMessage(), e);
            } catch (NoSuchVerseException e) {
                log.error(e.getMessage(), e);
            } catch (NoSuchKeyException e) {
                log.error(e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    public List<Verse> getVerses(String bibleId, String chapterId) {
        List<Verse> output = Lists.newLinkedList();
        Bible bible = getBible(bibleId);
        int count = 0;
        if (bible instanceof SwordBible swordBible) {
            Versification versification = swordBible.getSwordBook().getVersification();
            try {
                PassageTally p = new PassageTally(versification, chapterId);
                output = Streams.stream(p.iterator())
                        .map(key -> (org.crosswire.jsword.passage.Verse) key)
                        .map(
                                verse -> {
                                    SwordVerse v = SwordVerse.from(verse, bibleId);

                                    return (Verse) v;
                                }
                        ).toList();
            } catch (NoSuchVerseException e) {
                throw new RuntimeException(e);
            }
        }

        return output;
    }

    @Override
    public List<? extends BibleBook> getBibleBooks(Bible bible) {
        if (bible instanceof SwordBible swordBible && swordBible.getSwordBook() instanceof SwordBook swordBook) {
            Versification versification = swordBook.getVersification();
            List<SwordBibleBook> books = Streams.stream(versification.getBookIterator())
                    .filter(b -> !b.name().startsWith("INTRO"))
                    .map(b -> fromBook(b, bible, versification))
                    .toList();
            return books;
        }
        return null;
    }

    private SwordBibleBook fromBook(org.crosswire.jsword.versification.BibleBook bibleBook, Bible
            bible, Versification versification) {
        SwordBibleBook output = new SwordBibleBook(bibleBook, bible, versification);

        output.setId(bibleBook.getOSIS());
        BookName name = versification.getBookName(bibleBook);
        output.setAbbreviation(name.getShortName());
        output.setName(name.getLongName());
        return output;
    }
}
