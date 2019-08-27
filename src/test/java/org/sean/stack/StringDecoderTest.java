package org.sean.stack;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringDecoderTest {
    private StringDecoder decoder = new StringDecoder();

    @Test
    public void testStr() {
        Assert.assertEquals("accaccacc", decoder.decodeString("3[a2[c]]"));
        Assert.assertEquals("abcabccdcdcdef", decoder.decodeString("2[abc]3[cd]ef"));

        Assert.assertEquals("aaabFFFFcbFFFFc", decoder.decodeString("3[a]2[b4[F]c]"));
    }

    @Test
    public void decodeLongString() {
        String t = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
        Assert.assertEquals("zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef",
                decoder.decodeString(t));
    }
}