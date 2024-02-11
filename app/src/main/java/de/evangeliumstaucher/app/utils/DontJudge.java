package de.evangeliumstaucher.app.utils;

import de.evangeliumstaucher.invoker.ApiException;

import java.time.Duration;

public class DontJudge {

    public static long getTimePoints(Duration duration) {
        long sec = duration.getSeconds();
        if (duration.getSeconds() > 100) {
            //linear
            return sec;
        }
        return (long) (48 + 50 * Math.sin(0.0280756 * sec - 1.448));
    }

    public static int getDiffPoints(int diff) {
        return (int) Math.sqrt(diff);
    }
}