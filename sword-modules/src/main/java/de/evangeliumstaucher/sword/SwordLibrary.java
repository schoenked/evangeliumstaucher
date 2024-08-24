package de.evangeliumstaucher.sword;

import com.google.common.collect.Streams;
import de.evangeliumstaucher.repo.model.Bible;
import de.evangeliumstaucher.repo.model.BibleBook;
import de.evangeliumstaucher.repo.model.Passage;
import de.evangeliumstaucher.repo.model.Verse;
import de.evangeliumstaucher.repo.service.Library;
import de.evangeliumstaucher.sword.model.SwordBible;
import de.evangeliumstaucher.sword.model.SwordBibleBook;
import lombok.RequiredArgsConstructor;
import org.crosswire.jsword.book.sword.SwordBook;
import org.crosswire.jsword.passage.NoSuchVerseException;
import org.crosswire.jsword.passage.PassageTally;
import org.crosswire.jsword.versification.Versification;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
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
        return null;
    }

    @Override
    public List<Verse> getVerses(String bibleId, String chapterId) {
        Bible bible = getBible(bibleId);
        int count = 0;
        if (bible instanceof SwordBible swordBible) {
            Versification versification = swordBible.getSwordBook().getVersification();
            try {
                PassageTally p = new PassageTally(versification, chapterId);
                count = p.countVerses();
            } catch (NoSuchVerseException e) {
                throw new RuntimeException(e);
            }
        }
        LinkedList<Verse> output = new LinkedList<Verse>();
        for (int i = 1; i <= count; i++) {
            SwordVerse v = new SwordVerse();
            v.setText(Integer.toString(i));
            v.setBibleId(bibleId);
            v.setId(chapterId + ":" + i);
            output.add(v);
        }
        return output;
    }

    @Override
    public List<? extends BibleBook> getBibleBooks(Bible bible) {
        if (bible instanceof SwordBible swordBible && swordBible.getSwordBook() instanceof SwordBook swordBook) {
            Versification versification = swordBook.getVersification();
            List<SwordBibleBook> books = Streams.stream(versification.getBookIterator())
                    .filter(b -> !b.name().startsWith("Intro"))
                    .map(b -> fromBook(b, bible, versification))
                    .toList();
            return books;
        }
        return null;
    }

    private SwordBibleBook fromBook(org.crosswire.jsword.versification.BibleBook bibleBook, Bible bible, Versification versification) {
        SwordBibleBook output = new SwordBibleBook(bibleBook, bible, versification);

        output.setId(bibleBook.getOSIS());
        String name = versification.getBookName(bibleBook).getShortName();
        output.setAbbreviation(name);
        return output;
    }
}
