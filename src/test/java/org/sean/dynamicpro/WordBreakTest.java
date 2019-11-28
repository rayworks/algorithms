package org.sean.dynamicpro;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class WordBreakTest {

    @Test
    public void wordBreak() {
        WordBreak wordBreak = new WordBreak();
        Assert.assertFalse(
                wordBreak.wordBreak(
                        "catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }
}
