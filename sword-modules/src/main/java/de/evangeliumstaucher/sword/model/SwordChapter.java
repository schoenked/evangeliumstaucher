package de.evangeliumstaucher.sword.model;

import de.evangeliumstaucher.repo.model.BibleBook;
import de.evangeliumstaucher.repo.model.Chapter;
import lombok.Data;

@Data
public class SwordChapter implements Chapter {
    private String bibleId;

    private String number;

    private String id;

    @Override
    public String getText() {
        return bibleBook.getName() + " " + number;
    }

    private BibleBook bibleBook;
}
