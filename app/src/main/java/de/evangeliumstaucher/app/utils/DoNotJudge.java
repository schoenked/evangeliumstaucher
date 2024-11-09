package de.evangeliumstaucher.app.utils;

import java.time.Duration;

/**
 * You shall not judge, but...
 */
public class DoNotJudge {

    public static int getTimePointsSubtract(Duration duration) {
        int sec = (int) duration.getSeconds();
        if (duration.getSeconds() > 100) {
            //linear
            return sec;
        }
        return (int) (48 + 50 * Math.sin(0.0280756 * sec - 1.448));
    }

    public static int getDiffPoints(int diff) {
        return (int) Math.sqrt(diff);
    }
}
