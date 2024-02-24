package de.evangeliumstaucher.app.utils;

import java.time.Duration;

public class DontJudge {

    public static long getTimePointsSubtract(Duration duration) {
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
