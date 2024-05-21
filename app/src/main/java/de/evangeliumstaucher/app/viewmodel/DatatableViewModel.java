package de.evangeliumstaucher.app.viewmodel;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class DatatableViewModel {
    private final String id = UUID.randomUUID().toString();
    @Setter
    private List<DatatableColumn> columns;
    @Setter
    private String url;

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
