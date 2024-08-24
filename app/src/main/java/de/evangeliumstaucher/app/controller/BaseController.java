package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.viewmodel.BibleModel;
import de.evangeliumstaucher.repo.model.Bible;
import de.evangeliumstaucher.repo.service.Library;
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
    final Library library;

    protected void addWarning(Model m) {
        m.addAttribute("warning", "Leider kann die Bibel zurzeit nicht geladen werden. Versuch es bitte gleich nochmal.");
    }

    protected String getBible(Model m) {
        try {
            List<Bible> bibles = library.getBibles();

            List<Map.Entry<String, List<BibleModel>>> groups = bibles.stream()
                    .map(BibleModel::from)
                    .peek(b -> b.setUrl(b.getUrl()))
                    .collect(groupingBy(BibleModel::getLanguage))
                    .entrySet().stream()
                    .sorted((o1, o2) -> {
                        if (o1.getKey().equals("Deutsch")) return -1;
                        if (o2.getKey().equals("Deutsch")) return 1;
                        return o1.getKey().compareTo(o2.getKey());
                    })
                    .collect(Collectors.toList());
            groups = groups.subList(0, 2);

            m.addAttribute("languages", groups);
        } catch (Exception e) {
            log.error("failed", e);
            addWarning(m);
        }
        return "bible.html";
    }
}
