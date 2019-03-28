package org.sean.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LetterCombinationFinderTest {

    LetterCombinationFinder combinationFinder = new LetterCombinationFinder();

    @Test
    public void letterCombinations() {
        List<String> list = combinationFinder.letterCombinations("23");

        String[] expected = {"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"};

        Assert.assertArrayEquals(expected, list.toArray(new String[list.size()]));
    }
}