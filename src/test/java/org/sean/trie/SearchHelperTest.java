package org.sean.trie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SearchHelperTest {

    private SearchHelper helper;

    @Before
    public void setUp() throws Exception {
        helper = new SearchHelper();
    }

    @Test
    public void suggestedProducts() {
        List<List<String>> lists = helper.suggestedProducts(new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"}, "mouse");
        List<List<String>> expect = Arrays.asList(
                Arrays.asList("mobile", "moneypot", "monitor"),
                Arrays.asList("mobile", "moneypot", "monitor"),
                Arrays.asList("mouse", "mousepad"),
                Arrays.asList("mouse", "mousepad"),
                Arrays.asList("mouse", "mousepad")
        );
        Assert.assertEquals(expect.size(), lists.size());
        for (int i = 0; i < lists.size(); i++) {
            Assert.assertEquals(expect.get(i), lists.get(i));
        }
    }
}