package com.devrezaur.main.service;

import de.evangeliumstaucher.BiblesApi;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.BibleSummary;
import de.evangeliumstaucher.model.InlineResponse200;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BibleService {
    private final BiblesApi biblesApi;

    public List<BibleSummary> getBibles() throws ApiException {
        InlineResponse200 response = biblesApi.getBibles(null,null,null,null, true);
        return response.getData();
    }
}
