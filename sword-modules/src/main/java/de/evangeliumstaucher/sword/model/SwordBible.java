package de.evangeliumstaucher.sword.model;

import com.google.common.collect.Streams;
import de.evangeliumstaucher.repo.model.Bible;
import de.evangeliumstaucher.repo.model.Verse;
import de.evangeliumstaucher.sword.SwordVerse;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.crosswire.jsword.book.sword.SwordBook;
import org.crosswire.jsword.passage.NoSuchKeyException;
import org.crosswire.jsword.passage.NoSuchVerseException;
import org.crosswire.jsword.passage.PassageTally;

import java.util.Collections;
import java.util.List;

@Builder
@Data
@Slf4j
public class SwordBible implements Bible {
    private final SwordBook swordBook;
    String language;
    String languageCode;
    String abbreviation;
    String name;
    String description;
    String id;
    String copyright;

    private static boolean filterVerses(org.crosswire.jsword.passage.Verse key) {
        return key.getChapter() != 0
                && key.getVerse() != 0;
    }

    @Override
    public String getCopyright() {
        if (copyright == null) {
            copyright = (String) swordBook.getBookMetaData().getProperty("ShortCopyright");
        }
        return copyright;
    }

    @Override
    public List<Verse> getVerses(String whitelist, String blacklist) {

        List<Verse> verses;
        try {
            verses = Streams.stream(getSwordBook().getKey(whitelist).iterator())
                    .filter(key -> key instanceof org.crosswire.jsword.passage.Verse)
                    // blacklist filter
                    .filter(key -> {
                        try {
                            PassageTally blacklistkey = new PassageTally(getSwordBook().getVersification(), blacklist);
                            return !blacklistkey.contains(key);
                        } catch (NoSuchVerseException e) {
                            return false;
                        }
                    })
                    .map(key -> (org.crosswire.jsword.passage.Verse) key)
                    .filter(SwordBible::filterVerses)
                    .map(key -> {
                        return (Verse) SwordVerse.from(key, getId());
                    })
                    .toList();
        } catch (NoSuchKeyException e) {
            return verses = Collections.emptyList();
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

    @Override
    public boolean containsPassage(String id) {
        try {
            return swordBook.contains(new PassageTally(getSwordBook().getVersification(), id));
        } catch (NoSuchVerseException e) {
        }
        return false;
    }
}
