package com.devrezaur.main.model;

import de.evangeliumstaucher.model.Bible;
import lombok.Data;

import java.util.List;

@Data
public class BibleWrap extends Bible {
    private List<BookWrap> books;
}
