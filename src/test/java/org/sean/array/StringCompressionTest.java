package org.sean.array;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringCompressionTest {

    @Test
    public void compress() {
        String result = new StringCompression().compress("aabcccccaaa");
        assertEquals("Strings not equal", "a2b1c5a3", result);
    }
}