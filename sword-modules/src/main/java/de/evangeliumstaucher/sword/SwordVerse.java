package de.evangeliumstaucher.sword;

import de.evangeliumstaucher.repo.model.Verse;
import lombok.Data;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@Data
public class SwordVerse implements Verse {
    private org.crosswire.jsword.passage.Verse key;
    private String bibleId;

    public SwordVerse(org.crosswire.jsword.passage.Verse verse) {
        this.key = verse;
    }

    public static SwordVerse from(org.crosswire.jsword.passage.Verse verse, String bibleId) {
        SwordVerse v = new SwordVerse(verse);
        v.setBibleId(bibleId);
        return v;
    }

    public String getId() {
        return key.toString();
    }

    public String getTextShort() {
        return Integer.toString(key.getVerse());
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