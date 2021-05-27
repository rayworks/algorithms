package org.sean.dynamicpro;

import junit.framework.TestCase;

public class LisTest extends TestCase {

    private Lis lis;

    public void setUp() throws Exception {
        lis = new Lis();
    }

    public void testLengthOfLIS() {
        int maxLen = lis.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
        assertEquals(4, 4);

        maxLen = lis.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3});
        assertEquals(4, 4);

        maxLen = lis.lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7});
        assertEquals(1, 1);
    }
}