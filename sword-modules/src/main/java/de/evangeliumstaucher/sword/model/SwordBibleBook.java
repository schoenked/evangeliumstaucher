package de.evangeliumstaucher.sword.model;

import de.evangeliumstaucher.repo.model.Bible;
import de.evangeliumstaucher.repo.model.BibleBook;
import de.evangeliumstaucher.repo.model.Chapter;
import de.evangeliumstaucher.repo.model.Division;
import lombok.Data;
import org.crosswire.jsword.versification.DivisionName;
import org.crosswire.jsword.versification.Versification;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Division> getDivisions() {
        List<Division> divisions = Arrays.stream(DivisionName.values())
                .filter(divisionName -> divisionName.contains(getBibleBook()))
                .map(
                        divisionName -> {
                            return  SwordDivision.from(divisionName);
                        }).collect(Collectors.toUnmodifiableList());


        return divisions;
    }

}
