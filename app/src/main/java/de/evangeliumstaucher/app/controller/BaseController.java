package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.viewmodel.*;
import de.evangeliumstaucher.repo.model.Bible;
import de.evangeliumstaucher.repo.service.Library;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;

import java.util.List;
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

            List<DatatableRow> books = bibles.stream()
                    .map(BibleModel::from)
                    .peek(b -> b.setUrl(b.getUrl()))
                    .collect(groupingBy(BibleModel::getLanguageCode))
                    .entrySet().stream()
                    .sorted((o1, o2) -> {
                        //sort user locale matching to beginning
                        if (o1.getKey().equals(LocaleContextHolder.getLocale().getLanguage())) return -1;
                        if (o2.getKey().equals(LocaleContextHolder.getLocale().getLanguage())) return 1;
                        return o1.getKey().compareTo(o2.getKey());
                    })
                    .flatMap(g -> g.getValue().stream())
                    .map(e -> {
                        DatatableRow row = DatatableRow.builder()
                                .cells(List.of(
                                        new DatatableCell(e.getLanguage()),
                                        new DatatableCell(e.getLabel()),
                                        new DatatableCell("<a href=" + e.getUrl() + ">Auswählen</a>", true))
                                )
                                .build();
                        return row;
                    })
                    .collect(Collectors.toList());

            DatatableViewModel booksTable = new DatatableViewModel();
            List<DatatableColumn> columns = List.of(
                    new DatatableColumn("Sprache", "language"),
                    new DatatableColumn("Übersetzung", "bible"),
                    new DatatableColumn("", "select")
            );
            booksTable.setColumns(columns);
            booksTable.setRows(books);
            m.addAttribute("booksTable", booksTable);
        } catch (Exception e) {
            log.error("failed", e);
            addWarning(m);
        }
        return "bible.html";
    }
}
