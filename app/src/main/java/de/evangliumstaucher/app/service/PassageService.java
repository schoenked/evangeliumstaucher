package de.evangliumstaucher.app.service;

import de.evangeliumstaucher.PassagesApi;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Passage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassageService {
    private final PassagesApi passagesApi;

    public Passage getPassage(String bibleId, String passageId, String contentType, Boolean includeNotes, Boolean includeTitles, Boolean includeChapterNumbers, Boolean includeVerseNumbers, Boolean includeVerseSpans, String parallels, Boolean useOrgId) throws ApiException {
        return passagesApi.getPassage(bibleId, passageId, contentType, includeNotes, includeTitles, includeChapterNumbers, includeVerseNumbers, includeVerseSpans, parallels, useOrgId).getData();
    }
}
