package org.sean.array;

import org.junit.Assert;
import org.junit.Test;
import org.sean.array.ElementFinder;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sean on 2/24/17.
 */
public class ElementFinderTest {
    @Test
    public void testMissingNums() {
        ElementFinder finder = new ElementFinder();

        int[] all = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> result = finder.findDisappearedNumbers(all);

        Integer[] array = result.toArray(new Integer[result.size()]);
        System.out.printf(Arrays.toString(array));
        Assert.assertArrayEquals(new Integer[]{5, 6}, array);
    }
}
