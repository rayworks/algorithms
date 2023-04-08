package org.sean.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InformTimeTest {

    private InformTime informTime;

    @Before
    public void setUp() throws Exception {
        informTime = new InformTime();
    }

    @Test
    public void numOfMinutes() {
        int minutes = informTime.numOfMinutes(11, 4, new int[]{5, 9, 6, 10, -1, 8, 9, 1, 9, 3, 4},
                new int[]{0, 213, 0, 253, 686, 170, 975, 0, 261, 309, 337});

        assertEquals(2560, minutes);
    }
}