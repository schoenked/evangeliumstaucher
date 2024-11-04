package de.evangeliumstaucher.app.viewmodel;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DatatableRow {
    private List<DatatableCell> cells;
}
