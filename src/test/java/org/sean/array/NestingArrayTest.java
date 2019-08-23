package org.sean.array;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class NestingArrayTest {

    @Test
    public void arrayNesting() {
        NestingArray array = new NestingArray();
        int len = array.arrayNesting(new int[]{5, 4, 0, 3, 1, 6, 2});
        Assert.assertEquals(4, len);
    }
}