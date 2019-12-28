package org.sean.recursive;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UniquePaths2Test {

    private UniquePaths2 uniquePaths2;

    @Before
    public void setUp() {
        uniquePaths2 = new UniquePaths2();
    }

    @Test
    public void uniquePathsWithObstacles() {
        int count =
                uniquePaths2.uniquePathsWithObstacles(
                        new int[][] {
                            new int[] {0, 0, 0},
                            new int[] {0, 1, 0},
                            new int[] {0, 0, 0}
                        });

        Assert.assertEquals(2, count);
    }

    @Test
    public void testEmptyPath() {
        int count = uniquePaths2.uniquePathsWithObstacles(new int[][] {new int[] {1}});

        Assert.assertEquals(0, count);
    }
}
