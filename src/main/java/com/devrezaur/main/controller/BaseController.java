package com.devrezaur.main.controller;

import com.devrezaur.main.service.*;
import com.devrezaur.main.viewmodel.BibleModel;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.BibleSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@RequiredArgsConstructor
public class BaseController {
    protected final BibleService bibleService;
    protected final BookService bookService;
    protected final ChaptersService chaptersService;
    protected final VersesService versesService;
    protected final PassageService passageService;

    protected void addWarning(Model m) {
        m.addAttribute("warning", "Leider kann die Bibel zurzeit nicht geladen werden. Versuch es bitte gleich nochmal.");
    }

    protected String getBible(Model m) {
        try {
            List<BibleSummary> bibles = bibleService.getBibles();

            List<Map.Entry<String, List<BibleModel>>> groups = bibles.stream()
                    .map(BibleModel::from)
                    .peek(b -> b.setUrl("/bible/" + b.getUrl()))
                    .collect(groupingBy(BibleModel::getLanguage))
                    .entrySet().stream()
                    .sorted((o1, o2) -> {
                        if (o1.getKey().equals("Deutsch")) return -1;
                        if (o2.getKey().equals("Deutsch")) return 1;
                        return o1.getKey().compareTo(o2.getKey());
                    })
                    .collect(Collectors.toList());

            m.addAttribute("languages", groups);
        } catch (ApiException e) {
            log.error("failed", e);
            addWarning(m);
        }
        return "bible.html";
    }
}
