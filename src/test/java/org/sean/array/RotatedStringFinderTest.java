package org.sean.array;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RotatedStringFinderTest {

    @Test
    public void isRotate() {
        RotatedStringFinder finder = new RotatedStringFinder();
        Assert.assertTrue(finder.isRotate("erbottlewat", "waterbottle"));
        Assert.assertTrue(finder.isRotate("ewaterbottle", "eewaterbottl"));
    }
}