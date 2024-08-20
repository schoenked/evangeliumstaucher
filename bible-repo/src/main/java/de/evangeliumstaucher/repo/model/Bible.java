package de.evangeliumstaucher.repo.model;

public interface Bible extends Model {

    String getLanguage();

    String getAbbreviation();

    String getName();

    String getDescription();
}
