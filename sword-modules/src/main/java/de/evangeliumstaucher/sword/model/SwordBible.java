package de.evangeliumstaucher.sword.model;

import de.evangeliumstaucher.repo.model.Bible;
import lombok.Builder;
import lombok.Data;
import org.crosswire.jsword.book.Book;

@Builder
@Data
public class SwordBible implements Bible {
    private final Book swordBook;
    String language;
    String abbreviation;
    String name;
    String description;
    String id;
}
