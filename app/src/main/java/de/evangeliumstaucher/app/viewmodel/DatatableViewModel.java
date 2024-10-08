package de.evangeliumstaucher.app.viewmodel;

import de.evangeliumstaucher.app.utils.DatatableLanguages;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class DatatableViewModel {
    private final String id = UUID.randomUUID().toString();
    @Setter
    private List<DatatableColumn> columns;
    @Setter
    private String url;
    @Getter
    @Setter
    private boolean autoReloader = false;

    public static String getLanguage(Locale locale) {
        if (locale != null) {
            if (DatatableLanguages.getAvailableLanguages().contains(locale.getDisplayLanguage(Locale.ENGLISH))) {
                return locale.getDisplayLanguage(Locale.ENGLISH);
            }
        }
        return "English";
    }

    public String getLanguage() {
        return getLanguage(LocaleContextHolder.getLocale());
    }

    /**
     * Javascript variables can't have -
     *
     * @return id without -
     */
    public String getJavascriptId() {
        return StringUtils.remove(id, "-");
    }
    public String getColumnsText() {
        return columns.stream()
                .map(this::getColumnText)
                .collect(Collectors.joining());
    }

    private String getColumnText(DatatableColumn c) {
        StringBuilder columnText = new StringBuilder();
        columnText.append("{data: '").append(c.getAttribute()).append("', ");
        columnText.append("name: '").append(c.getName()).append("', ");
        columnText.append("type: '").append(c.getType()).append("'");

        if (c.getType().equals("date")) {
            columnText.append(", render: function (data, type, row) {");
            columnText.append("return data != null ? moment(data).format('DD.MM.YYYY') : data;");
            columnText.append("}");
        }

        if (c.getType().equals("datetime")) {
            columnText.append(", render: function (data, type, row) {");
            columnText.append("return data != null ? moment(data).format('DD.MM.YYYY HH:mm') : data;");
            columnText.append("}");
        }

        columnText.append("},");
        return columnText.toString();
    }
}
