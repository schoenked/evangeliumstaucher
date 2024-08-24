package de.evangeliumstaucher.repo.service;

import de.evangeliumstaucher.repo.model.Bible;
import de.evangeliumstaucher.repo.model.BibleBook;
import de.evangeliumstaucher.repo.model.Passage;
import de.evangeliumstaucher.repo.model.Verse;

import java.util.List;

public interface Library {

    Bible getBible(String name);

    List<? extends BibleBook> getBibleBooks(String bibleName);

    List<? extends BibleBook> getBibleBooks(Bible bible);

    List<Bible> getBibles();

    Passage getPassage(String bibleId, String passageId);

    List<Verse> getVerses(String bibleId, String chapterId);

}
