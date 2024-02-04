package de.evangliumstaucher.app.service;

import de.evangeliumstaucher.BiblesApi;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.BibleSummary;
import de.evangeliumstaucher.model.InlineResponse200;
import de.evangliumstaucher.app.model.BibleWrap;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BibleService {
    private final BiblesApi biblesApi;

    @Cacheable("bibles")
    public List<BibleSummary> getBibles() throws ApiException {
        InlineResponse200 response = biblesApi.getBibles("deu", null, null, null, false);
        List<BibleSummary> list = response.getData();

        return list;
    }

    @Cacheable("bible")
    public BibleWrap get(String bibleId) {
        return new BibleWrap(bibleId);
    }
}
