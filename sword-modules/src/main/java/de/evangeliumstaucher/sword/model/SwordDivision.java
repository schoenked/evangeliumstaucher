package de.evangeliumstaucher.sword.model;

import de.evangeliumstaucher.repo.model.Division;
import lombok.RequiredArgsConstructor;
import org.crosswire.jsword.versification.DivisionName;

@RequiredArgsConstructor
public class SwordDivision  implements Division {
    private final DivisionName divisionName;

    @Override
    public int getSize() {
        return divisionName.getSize();
    }

    @Override
    public String getName() {
        return divisionName.getName();
    }
}
