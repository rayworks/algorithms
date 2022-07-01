package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaxCardPointsTest {

    private MaxCardPoints cardPoints;

    @Before
    public void setUp() throws Exception {
        cardPoints = new MaxCardPoints();
    }

    @Test
    public void maxScore() {
        assertEquals(232, cardPoints.maxScore(new int[]{11, 49, 100, 20, 86, 29, 72}, 4));
    }
}