package de.evangeliumstaucher.app.service;

import de.evangeliumstaucher.repo.model.Bible;
import de.evangeliumstaucher.repo.model.Passage;
import de.evangeliumstaucher.repo.model.Verse;
import de.evangeliumstaucher.sword.SwordLibrary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
}
