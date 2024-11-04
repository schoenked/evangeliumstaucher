package de.evangeliumstaucher.app.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DatatableCell {
    public final String text;
    public boolean rendered = false;
}
