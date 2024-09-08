package de.evangeliumstaucher.sword.model;

import de.evangeliumstaucher.repo.model.Bible;
import de.evangeliumstaucher.repo.model.BibleBook;
import de.evangeliumstaucher.repo.model.Chapter;
import lombok.Data;
import org.crosswire.jsword.versification.Versification;

import java.util.Collection;
import java.util.LinkedList;

@Data
public class SwordBibleBook implements BibleBook {
    private final org.crosswire.jsword.versification.BibleBook bibleBook;
    private final Bible bible;
    private final Versification versification;
    private Collection<Chapter> chapters;
    private String name;
    private String abbreviation;
    private String id;

    public Collection<Chapter> getChapters() {
        if (chapters == null) {
            chapters = new LinkedList<Chapter>();
            int count = versification.getLastChapter(bibleBook);
            for (int i = 1; i <= count; i++) {
                SwordChapter c = new SwordChapter();
                c.setBibleId(bible.getId());
                c.setId(id + " " + i);
                c.setNumber(Integer.toString(i));
                chapters.add(c);
            }
        }
        return chapters;
    }

}
