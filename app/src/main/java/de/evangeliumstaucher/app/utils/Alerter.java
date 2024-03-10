package de.evangeliumstaucher.app.utils;

import java.util.Arrays;
import java.util.Objects;

public class Alerter {

    public static String join(String... e) {
        return String.join(", ", Arrays.stream(e)
                .filter(Objects::nonNull)
                .toList()
        );
    }
}
