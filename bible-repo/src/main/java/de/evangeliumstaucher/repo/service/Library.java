package de.evangeliumstaucher.repo.service;

import de.evangeliumstaucher.repo.model.*;

import java.util.List;

public interface Library {

    Bible getBible(String name);

    List<? extends BibleBook> getBibleBooks(String bibleName);

    List<? extends BibleBook> getBibleBooks(Bible bible);

    List<? extends Division> getDivisions(Bible bible);

    List<Bible> getBibles();

    Passage getPassage(String bibleId, String passageId);

    List<Verse> getVerses(String bibleId, String chapterId);

}
