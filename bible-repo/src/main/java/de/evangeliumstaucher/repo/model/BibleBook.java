package de.evangeliumstaucher.repo.model;

import java.util.Collection;

public interface BibleBook extends Model {

    Collection<Chapter> getChapters();

    String getAbbreviation();
}
