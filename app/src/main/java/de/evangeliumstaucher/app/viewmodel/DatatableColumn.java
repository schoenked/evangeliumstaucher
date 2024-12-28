package de.evangeliumstaucher.app.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class DatatableColumn {
    private final String name;
    private final String attribute;
    private String type = "string";
    private boolean visible = true;
    private Order order = null;

    public DatatableColumn(String name, String attribute, String type) {
        this.name = name;
        this.attribute = attribute;
        this.type = type;
    }

    public DatatableColumn(String name, String attribute, String type, boolean visible) {
        this(name, attribute, type);
        this.visible = visible;
    }

    public DatatableColumn withOrder(Order order) {
        this.order = order;
        return this;
    }

    public enum Order {
        ASC,
        DESC
    }
}
