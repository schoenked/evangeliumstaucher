package de.evangeliumstaucher.sword.model;

import de.evangeliumstaucher.repo.model.Chapter;
import lombok.Data;

@Data
public class SwordChapter implements Chapter {
    private String bibleId;

    private String number;

    private String id;
}
