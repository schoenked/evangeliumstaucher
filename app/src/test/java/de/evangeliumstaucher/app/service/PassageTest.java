package de.evangeliumstaucher.app.service;

import de.evangeliumstaucher.app.model.BibleWrap;
import de.evangeliumstaucher.app.model.VerseWrap;
import de.evangeliumstaucher.repo.model.Bible;
import de.evangeliumstaucher.repo.model.Passage;
import de.evangeliumstaucher.repo.model.Verse;
import de.evangeliumstaucher.sword.SwordLibrary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
public class PassageTest {

    private static final String ID_SCHLACHTER = "sword-U2NobGFjaHRlciBCaWJlbCAoMTk1MSk=";
    @Autowired
    public SwordLibrary swordLibrary;

    @Test
    public void testOutputBibles() {
        for (Bible bible : swordLibrary.getBibles()) {
            System.out.println(bible.toString());
        }
    }

    @Test
    public void testKeys() {
        Passage p = swordLibrary.getPassage(ID_SCHLACHTER, "1Mo1:0");
        String c = p.getContent();
    }

    @Test
    public void testBooks() {
        Bible bible = swordLibrary.getBible(ID_SCHLACHTER);

        List<Verse> verses = bible.getVerses();
    }

    @Test
    public void testVerses() {
        Bible bible = swordLibrary.getBible(ID_SCHLACHTER);
        BibleWrap b = new BibleWrap(bible.getId(), bible);
        VerseWrap verse = b.getBooks(swordLibrary).getFirst().getChapters().getFirst().getVerses(swordLibrary).getFirst();
        for (int i = 0; i < 100; i++) {
            assertThat(verse.getTeXTLong()).isNotEmpty();
            verse = verse.stepVerses(1, swordLibrary);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(verse.getTeXTLong());
            assertThat(verse.getTeXTLong()).isNotEmpty();
            verse = verse.stepVerses(-6, swordLibrary);
        }
    }

    @Test
    public void testVersesNoChapter() {
        Bible bible = swordLibrary.getBible(ID_SCHLACHTER);
        BibleWrap b = new BibleWrap(bible.getId(), bible);
        //Judas has only 1 chapter this key is "Ch 1" verse 3
        VerseWrap.getVerse("Judas 3", b, swordLibrary);
    }

}
