package org.sean.backtracking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class FibonacciArraySplitterTest {

    private FibonacciArraySplitter splitter;

    @Before
    public void setUp() throws Exception {
        splitter = new FibonacciArraySplitter();
    }

    @Test
    public void splitIntoFibonacci() {
        assertEquals(Collections.emptyList(), splitter.splitIntoFibonacci("214748364721474836422147483641"));
        assertEquals(Arrays.asList(11,0,11,11), splitter.splitIntoFibonacci("1101111"));
    }
}