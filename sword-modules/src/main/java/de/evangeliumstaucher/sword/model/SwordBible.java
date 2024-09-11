package de.evangeliumstaucher.sword.model;

import com.google.common.collect.Streams;
import de.evangeliumstaucher.repo.model.Bible;
import de.evangeliumstaucher.repo.model.Verse;
import de.evangeliumstaucher.sword.SwordVerse;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.crosswire.jsword.book.sword.SwordBook;

import java.util.List;

@Builder
@Data
public class SwordBible implements Bible {
    private final SwordBook swordBook;
    String language;
    String languageCode;
    String abbreviation;
    String name;
    String description;
    String id;
    @ToString.Exclude
    private List<Verse> verses;

    @Override
    public List<Verse> getVerses() {
        if (verses == null) {

            verses = Streams.stream(getSwordBook().getGlobalKeyList().iterator())
                    .filter(key -> key instanceof org.crosswire.jsword.passage.Verse)
                    .map(key -> {
                        org.crosswire.jsword.passage.Verse verse = (org.crosswire.jsword.passage.Verse) key;
                        return (Verse) SwordVerse.from(verse, getId());
                    })
                    .toList();
        }
        return verses;
    }

    @Override
    public Verse getVerse(String verseId) {
        org.crosswire.jsword.passage.Verse key;
        try {
            key = (org.crosswire.jsword.passage.Verse) getSwordBook().getKey(verseId).get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return SwordVerse.from(key, getId());
    }
}
