package com.devrezaur.main.service;

import de.evangeliumstaucher.VersesApi;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.VerseSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VersesService {
    private static final String TAG = VersesService.class.getSimpleName();
    private final VersesApi versesApi;

    public List<VerseSummary> getVerses(String bibleId, String chapterId) throws ApiException {
        log.debug("getVerses() called with: bibleId = [" + bibleId + "], chapterId = [" + chapterId + "]");
        return versesApi.getVerses(bibleId, chapterId).getData();
    }
}
