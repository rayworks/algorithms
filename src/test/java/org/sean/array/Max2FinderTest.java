package org.sean.array;

import org.junit.Assert;
import org.junit.Test;

public class Max2FinderTest {

    @Test
    public void getMax2() {
        Max2Finder finder = new Max2Finder();
        int[] array = new int[]{231, 12, -97, -99, 13, 0, Integer.MIN_VALUE, Integer.MAX_VALUE};
        int[] result = finder.getMax(array);

        Assert.assertArrayEquals(new int[]{Integer.MAX_VALUE, 231}, result);
    }

}