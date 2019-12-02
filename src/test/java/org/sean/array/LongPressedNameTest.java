package org.sean.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LongPressedNameTest {

    private LongPressedName longPressedName;

    @Before
    public void setUp() throws Exception {
        longPressedName = new LongPressedName();
    }

    @Test
    public void isLongPressedName() {
        Assert.assertTrue(longPressedName.isLongPressedName("leelee", "lleeelee"));
    }

    @Test
    public void isNotLongPressedName() {
        Assert.assertFalse(longPressedName.isLongPressedName("saeed", "ssaaedd"));
    }
}