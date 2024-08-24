package de.evangeliumstaucher.sword.model;

import de.evangeliumstaucher.repo.model.Bible;
import lombok.Builder;
import lombok.Data;
import org.crosswire.jsword.book.sword.SwordBook;

@Builder
@Data
public class SwordBible implements Bible {
    private final SwordBook swordBook;
    String language;
    String abbreviation;
    String name;
    String description;
    String id;
}
