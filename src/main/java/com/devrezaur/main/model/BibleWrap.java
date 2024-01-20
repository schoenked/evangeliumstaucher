package com.devrezaur.main.model;

import de.evangeliumstaucher.model.Bible;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class BibleWrap {
    private List<BookWrap> books;
    @ToString.Exclude
    private Bible bible;

    public BookWrap getLast() {
        return books.get(books.size() - 1);
    }
}
