package de.evangeliumstaucher.sword;

import de.evangeliumstaucher.repo.model.Verse;
import lombok.Data;

@Data
public class SwordVerse implements Verse {
    private String text;
    private String bibleId;
    private String id;
}