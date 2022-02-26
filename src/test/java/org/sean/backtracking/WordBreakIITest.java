package org.sean.backtracking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class WordBreakIITest {

    private WordBreakII wordBreak;

    @Before
    public void setUp() throws Exception {
        wordBreak = new WordBreakII();
    }

    @Test
    public void wordBreak() {
        List<String> output =
                wordBreak.wordBreak(
                        "catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));

        List<String> expected = Arrays.asList("cat sand dog", "cats and dog");
        Assert.assertEquals(expected.size(), output.size());
        Assert.assertTrue(output.containsAll(expected));
    }
}
