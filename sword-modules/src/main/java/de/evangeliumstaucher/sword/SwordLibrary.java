package de.evangeliumstaucher.sword;

import de.evangeliumstaucher.repo.model.Bible;
import de.evangeliumstaucher.repo.model.BibleBook;
import de.evangeliumstaucher.repo.model.Passage;
import de.evangeliumstaucher.repo.model.Verse;
import de.evangeliumstaucher.repo.service.Library;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SwordLibrary implements Library {

    private final BibleService bibleService;

    @Override
    public Bible getBible(String name) {
        return bibleService.getBible(name);
    }

    @Override
    public List<BibleBook> getBibleBooks(String bibleName) {
        return List.of();
    }

    @Override
    public List<Bible> getBibles() {
        return bibleService.getBibles();
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
