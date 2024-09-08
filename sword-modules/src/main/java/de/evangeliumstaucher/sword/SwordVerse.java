package de.evangeliumstaucher.sword;

import de.evangeliumstaucher.repo.model.Verse;
import lombok.Data;
import org.crosswire.jsword.passage.VerseKey;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@Data
public class SwordVerse implements Verse {
    private String textShort;
    private VerseKey key;
    private String bibleId;
    private String id;

    public static SwordVerse from(org.crosswire.jsword.passage.Verse verse, String bibleId) {
        SwordVerse v = new SwordVerse();
        int index = verse.getVerse();
        v.setTextShort(Integer.toString(index));
        v.setKey(verse);
        v.setBibleId(bibleId);
        v.setId(verse.toString());
        return v;
    }

    @Override
    public String getTeXTLong() {
        String text = key.getName();
        if (LocaleContextHolder.getLocale() == Locale.GERMAN) {
            text.replace(":", ",");
        }
        return text;
    }
}