package de.evangeliumstaucher.sword;

import de.evangeliumstaucher.repo.model.Bible;
import de.evangeliumstaucher.repo.model.BibleBook;
import de.evangeliumstaucher.repo.model.Passage;
import de.evangeliumstaucher.repo.model.Verse;
import de.evangeliumstaucher.repo.service.Library;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwordLibrary implements Library {
    @Override
    public Bible getBible(String name) {
        return null;
    }

    @Override
    public List<BibleBook> getBibleBooks(String bibleName) {
        return List.of();
    }

    @Override
    public List<Bible> getBibles() {
        return List.of();
    }

    @Override
    public Passage getPassage(String bibleId, String passageId) {
        return null;
    }

    @Override
    public List<Verse> getVerses(String bibleId, String chapterId) {
        return List.of();
    }
}
