package com.devrezaur.main.service;

import de.evangeliumstaucher.BiblesApi;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.BibleSummary;
import de.evangeliumstaucher.model.InlineResponse200;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BibleService {
    private final BiblesApi biblesApi;
    Map<String, String> abbreviationIdMap;

    public List<BibleSummary> getBibles() throws ApiException {
        InlineResponse200 response = biblesApi.getBibles(null, null, null, null, true);
        List<BibleSummary> list = response.getData();

      /*  Map<String, String> abbreviationIdMap = list.stream()
                .collect(Collectors.toMap(bibleSummary -> bibleSummary.getLanguage().getId() + "/"+ bibleSummary.getAbbreviationLocal(),
                        BibleSummary::getId));
*/
        return list;

    }
/*

    public String getIdByAbbreviation(String languageId, String abbreviation) throws ApiException {
        if (abbreviationIdMap == null) {
            getBibles();
        }
        if (abbreviationIdMap != null) {
            return abbreviationIdMap.get(abbreviation);
        }
        return null;
    }*/
}
