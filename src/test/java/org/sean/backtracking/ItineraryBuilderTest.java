package org.sean.backtracking;

import org.junit.Before;
import org.junit.Test;
import org.sean.dynamicpro.Lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ItineraryBuilderTest {

    private ItineraryBuilder builder;

    @Before
    public void setUp() {
        builder = new ItineraryBuilder();
    }
    @Test
    public void findItinerary() {
        ArrayList<List<String>> arrayList = new ArrayList<>();

        arrayList.add(Arrays.asList("JFK", "SFO"));
        arrayList.add(Arrays.asList("JFK", "ATL"));
        arrayList.add(Arrays.asList("SFO", "ATL"));
        arrayList.add(Arrays.asList("ATL", "JFK"));
        arrayList.add(Arrays.asList("ATL", "SFO"));

        List<String> actual = builder.findItinerary(arrayList);
        List<String> expected = Arrays.asList("JFK","ATL","JFK","SFO","ATL","SFO");
        assertTrue(actual.containsAll(expected) && expected.containsAll(actual));
    }
}