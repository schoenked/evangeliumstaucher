package de.evangeliumstaucher.repo.model;

import de.evangeliumstaucher.repo.service.Library;

import java.util.Collection;

public interface BibleBook extends Model {

    Collection<Chapter> getChapters(Library library);

    String getAbbreviation();
}
