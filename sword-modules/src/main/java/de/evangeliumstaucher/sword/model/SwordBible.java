package de.evangeliumstaucher.sword.model;

import com.google.common.collect.Streams;
import de.evangeliumstaucher.repo.model.Bible;
import de.evangeliumstaucher.repo.model.Verse;
import de.evangeliumstaucher.sword.SwordVerse;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.crosswire.jsword.book.sword.SwordBook;
import org.crosswire.jsword.passage.Key;
import org.crosswire.jsword.passage.NoSuchKeyException;
import org.crosswire.jsword.passage.NoSuchVerseException;
import org.crosswire.jsword.passage.PassageTally;

import java.util.Collections;
import java.util.HashMap;
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
    //whitelist and verses
    private HashMap<String, List<Key>> verses;
    private HashMap<String, Key> keys;

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
            verses = getVerses(whitelist)
                    .stream()
                    .filter(key -> key instanceof org.crosswire.jsword.passage.Verse)
                    // blacklist filter
                    .filter(key -> {
                        try {
                            Key blacklistkey = getKey(blacklist);
                            return !blacklistkey.contains(key);
                        } catch (NoSuchKeyException e) {
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

    private List<Key> getVerses(String whitelist) throws NoSuchKeyException {
        if (verses == null) {
            verses = new HashMap<>();
        }
        if (verses.containsKey(whitelist)) {
            return verses.get(whitelist);
        } else {
            List<Key> v = Streams.stream(getKey(whitelist).iterator())
                    .toList();
            verses.put(whitelist, v);
            return v;
        }
    }

    @Override
    public Verse getVerse(String verseId) {
        org.crosswire.jsword.passage.Verse key;
        try {
            key = (org.crosswire.jsword.passage.Verse) getKey(verseId).get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return SwordVerse.from(key, getId());
    }

    private Key getKey(String id) throws NoSuchKeyException {
        if (keys == null) {
            keys = new HashMap<>();
        }
        if (keys.containsKey(id)) {
            return keys.get(id);
        } else {
            Key k = getSwordBook().getKey(id);
            keys.put(id, k);
            return k;
        }
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
