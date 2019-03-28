package org.sean.array;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LetterCombinationFinderTest {

    LetterCombinationFinder combinationFinder = new LetterCombinationFinder();

    @Test
    public void letterCombinations() {
        List<String> list = combinationFinder.letterCombinations("234");
        System.out.println(list);
    }
}