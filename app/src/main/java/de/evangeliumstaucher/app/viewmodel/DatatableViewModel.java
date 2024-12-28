package de.evangeliumstaucher.app.viewmodel;

import de.evangeliumstaucher.app.utils.DatatableLanguages;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class DatatableViewModel {
    private final String id = UUID.randomUUID().toString();
    private List<DatatableColumn> columns;
    private String url;
    private boolean autoReloader = false;
    private List<DatatableRow> rows;
    private int pageLength = 20;
    @Getter
    private String order;
    @Getter
    private String orderColumn;

    public static String getLanguage(Locale locale) {
        if (locale != null) {
            if (DatatableLanguages.getAvailableLanguages().contains(locale.getDisplayLanguage(Locale.ENGLISH))) {
                return locale.getDisplayLanguage(Locale.ENGLISH);
            }
        }
        return "English";
    }

    public void setColumns(List<DatatableColumn> columns) {
        this.columns = columns;
        for (DatatableColumn column : columns) {
            if (column.getOrder() != null) {
                this.orderColumn = column.getName();
                this.order = column.getOrder().toString().toLowerCase();
                return;
            }
        }
    }

    public int getPageLength() {
        return pageLength;
    }

    public void setPageLength(int pageLength) {
        this.pageLength = pageLength;
    }

    public String getLanguage() {
        return getLanguage(LocaleContextHolder.getLocale());
    }

    public boolean getServerside() {
        return StringUtils.isNotEmpty(getUrl());
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
        columnText.append("type: '").append(c.getType()).append("', ");
        columnText.append("visible: ").append(c.isVisible());

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
