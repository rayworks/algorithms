package org.sean.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SmallStrWithSwapsTest {

    private SmallStrWithSwaps obj;

    @Before
    public void setUp() throws Exception {
        obj = new SmallStrWithSwaps();
    }

    @Test
    public void smallestStringWithSwaps() {
        ArrayList<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(0, 3));
        list.add(Arrays.asList(1, 2));
        list.add(Arrays.asList(0, 2));

        assertEquals("abcd", obj.smallestStringWithSwaps("dcab", list));
    }
}