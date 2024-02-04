package de.evangliumstaucher.app.utils;

import java.util.List;
import java.util.Random;

public class ListUtils {
    public static <T> T randomItem(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
}
