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
}
