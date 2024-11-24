package de.evangeliumstaucher.repo.model;

import java.util.List;

public interface Bible extends Model {

    String getLanguage();

    String getLanguageCode();

    String getAbbreviation();

    String getName();

    String getDescription();

    List<Verse> getVerses();

    Verse getVerse(String verseId);

    boolean containsPassage(String id);

    String getCopyright();
}
