package de.evangeliumstaucher.app.utils;

import java.util.ArrayList;
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

    public static <T> List<List<T>> groupsOf(List<T> list, int groupSize) {
        List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i += groupSize) {
            result.add(list.subList(i, Math.min(i + groupSize, list.size())));
        }
        return result;
    }
}
