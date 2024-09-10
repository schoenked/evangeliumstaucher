package de.evangeliumstaucher.sword.model;

import com.google.common.collect.Iterators;
import de.evangeliumstaucher.repo.model.Division;
import lombok.RequiredArgsConstructor;
import org.crosswire.jsword.versification.DivisionName;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RequiredArgsConstructor
public class SwordDivision implements Division {
    private static final Iterator<String> colorIterator = Iterators.cycle(
            "primary",
            "secondary",
            "third",
            "fifth",
            "fourth",
            "seventh",
            "sixth"
    );
    private static final Map<DivisionName, SwordDivision> map = new HashMap<>();
    private final DivisionName divisionName;
    private String color;

    public static SwordDivision from(DivisionName divisionName) {
        if (!map.containsKey(divisionName)) {
            map.put(divisionName, new SwordDivision(divisionName));
        }
        return map.get(divisionName);
    }

    @Override
    public int getSize() {
        return divisionName.getSize();
    }

    @Override
    public String getName() {
        return divisionName.getName();
    }

    @Override
    public String getColor() {
        if (color == null) {
            color = colorIterator.next();
        }
        return color;
    }
}
