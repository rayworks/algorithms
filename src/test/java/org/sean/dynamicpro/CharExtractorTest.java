package org.sean.dynamicpro;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharExtractorTest {

    private CharExtractor extractor;

    @Before
    public void setUp() throws Exception {
        extractor = new CharExtractor();
    }

    @Test
    public void minExtraChar() {
        int res = extractor.minExtraChar("metzeaencgpgvsckjrqafkxgyzbe",
                new String[]{
                        "zdzz", "lgrhy", "r", "ohk", "zkowk", "g", "zqpn", "anoni", "ka", "qafkx", "t", "jr", "xdye",
                        "mppc", "bqqb", "encgp", "yf", "vl", "ctsxk", "gn", "cujh", "ce", "rwrpq", "tze", "zxhg", "yzbe",
                        "c", "o", "hnk", "gv", "uzbc", "xn", "kk", "ujjd", "vv", "mxhmv", "ugn", "at", "kumr", "ensv",
                        "x", "uy", "gb", "ae", "jljuo", "xqkgj"
                });
        Assert.assertEquals(5, res);
    }
}