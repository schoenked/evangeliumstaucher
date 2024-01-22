package com.devrezaur.main.utils;

import org.junit.Test;

import java.time.Duration;

import static com.devrezaur.main.utils.DontJudge.getDiffPoints;
import static com.devrezaur.main.utils.DontJudge.getTimePoints;
import static com.google.common.truth.Truth.assertThat;

public class DontJudgeTest {

    @Test
    public void testTime() {

        assertThat(getTimePoints(Duration.ofSeconds(0))).isAtMost(0);
        assertThat(getTimePoints(Duration.ofSeconds(10))).isAtLeast(1);
        assertThat(getTimePoints(Duration.ofSeconds(10))).isAtMost(2);
        assertThat(getTimePoints(Duration.ofSeconds(100))).isAtLeast(90);
        assertThat(getTimePoints(Duration.ofSeconds(100))).isAtMost(100);
        assertThat(getTimePoints(Duration.ofSeconds(500))).isEqualTo(500);
        assertThat(getTimePoints(Duration.ofSeconds(1000))).isEqualTo(1000);
        for (int i = 0; i < 200; i++) {
            System.out.println("sec = " + i + " results points: " + getTimePoints(Duration.ofSeconds(i)));
        }
    }

    @Test
    public void testDiff() {

        assertThat(getDiffPoints(0)).isEqualTo(0);
        assertThat(getDiffPoints(1)).isEqualTo(1);
        assertThat(getDiffPoints(10)).isEqualTo(3);
        assertThat(getDiffPoints(100)).isEqualTo(10);

    }
}