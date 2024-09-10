package de.evangeliumstaucher.repo.model;

import java.util.Collection;
import java.util.List;

public interface BibleBook extends Model {

    Collection<Chapter> getChapters();

    String getAbbreviation();
    String getName();
    List<Division> getDivisions();
}
