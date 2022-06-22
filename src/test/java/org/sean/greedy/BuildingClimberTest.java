package org.sean.greedy;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class BuildingClimberTest {

    private BuildingClimber climber;

    @Before
    public void setup() {
        climber = new BuildingClimber();
    }

    @Test
    public void testSmallBuildings() {
        int pos = climber.furthestBuilding(new int[]{4, 12, 2, 7, 3, 18, 20, 3, 19}, 10, 2);
        assertEquals(7, pos);
    }

    @Test
    @FileParameters(value = "src/test/resources/building_heights.csv", encoding = "UTF-8")
    public void furthestBuilding(String[] strings) {
        int[] heights = new int[strings.length];

        int i = 0;
        for (String s : strings) {
            heights[i++] = Integer.parseInt(s);
        }

        int index = climber.furthestBuilding(heights, 33671263, 108);
        assertEquals(589, index);
    }
}