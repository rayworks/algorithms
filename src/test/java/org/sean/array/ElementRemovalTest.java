package org.sean.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElementRemovalTest {
    private ElementRemoval elementRemoval;
    @Before
    public void setUp() throws Exception {
        elementRemoval = new ElementRemoval(new int[]{0,1,2,2,3,0,4,2});
    }

    @Test
    public void removeElement() {
        int r = elementRemoval.removeElement(2);
        Assert.assertEquals(5, r);
    }
}