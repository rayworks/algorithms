package org.sean.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.function.ToIntFunction;

import static org.junit.Assert.*;

public class AnagramFinderTest {

    private AnagramFinder finder;

    @Before
    public void setUp() throws Exception {
        finder = new AnagramFinder();
    }

    @Test
    public void findAnagrams() {
        List<Integer> anagramPositions = finder.findAnagrams("cbaebabacd", "abc");
        Assert.assertArrayEquals(new int[]{0, 6},
                anagramPositions.stream().mapToInt(i -> i).toArray()
        );
    }
}