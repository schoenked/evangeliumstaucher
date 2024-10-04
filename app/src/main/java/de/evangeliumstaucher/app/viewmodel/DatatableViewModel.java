package de.evangeliumstaucher.app.viewmodel;

import de.evangeliumstaucher.app.utils.DatatableLanguages;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

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

    public String getLanguage() {
        return getLanguage(Locale.getDefault());
    }

    public static String getLanguage(Locale locale) {
        if (locale != null) {
            if (DatatableLanguages.getAvailableLanguages().contains(locale.getDisplayLanguage(Locale.ENGLISH))) {
                return locale.getDisplayLanguage(Locale.ENGLISH);
            }
        }
        return "English";
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
                .map(c -> "{data: '" + c.getAttribute()
                        + "', name: '" + c.getName() + "'},")
                .collect(Collectors.joining());
    }
}
