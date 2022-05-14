package org.sean.graph;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NetworkDelayTimeTest {
    private NetworkDelayTime delayTime;

    @Before
    public void setUp() throws Exception {
        delayTime = new NetworkDelayTime();
    }

    @Test
    public void networkDelayTime() {
        int delay = delayTime.networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 2}}, 3, 1);
        assertEquals(2, delay);
    }
}