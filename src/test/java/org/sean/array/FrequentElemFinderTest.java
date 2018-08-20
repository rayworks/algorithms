package org.sean.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FrequentElemFinderTest {

    @Test
    public void topKFrequent() {
        FrequentElemFinder finder = new FrequentElemFinder();

        Integer[] actuals;
        List<Integer> list;

        list = finder.topKFrequent(new int[]{4, 1, -1, 2, -1, 2, 3}, 2);
        actuals = list.toArray(new Integer[list.size()]);
        System.out.println(Arrays.toString(actuals));
        Assert.assertArrayEquals(new Integer[]{2, -1}, actuals);

        list = finder.topKFrequent(new int[]{3,0,1,0}, 1);
        actuals = list.toArray(new Integer[list.size()]);
        System.out.println(Arrays.toString(actuals));
        Assert.assertArrayEquals(new Integer[]{0}, actuals);
    }
}