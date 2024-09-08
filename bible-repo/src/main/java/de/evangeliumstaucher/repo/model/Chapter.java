package de.evangeliumstaucher.repo.model;

public interface Chapter extends Model {

    String getBibleId();

    String getNumber();
    String getText();
    BibleBook getBibleBook();
}
