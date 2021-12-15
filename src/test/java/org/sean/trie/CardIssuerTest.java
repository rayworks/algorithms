package org.sean.trie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CardIssuerTest {

    private CardIssuer cardIssuer;

    @Before
    public void setUp() throws Exception {
        cardIssuer = new CardIssuer();
    }

    @Test
    public void testDetectNetwork() {
        Assert.assertEquals("Amex", cardIssuer.detectNetwork("341234567890123"));
        Assert.assertEquals("Amex", cardIssuer.detectNetwork("371234567890123"));
        Assert.assertEquals("Diners", cardIssuer.detectNetwork("38123456789012"));
        Assert.assertEquals("Discover", cardIssuer.detectNetwork("6221271234567890123"));
        Assert.assertEquals(null, cardIssuer.detectNetwork("6221271234567890123123"));

        Assert.assertEquals("Maestro", cardIssuer.detectNetwork("6221251234567890123"));
        Assert.assertEquals(null, cardIssuer.detectNetwork("62212512345678901234"));
        Assert.assertEquals(null, cardIssuer.detectNetwork("62212512345"));

        Assert.assertEquals("Visa", cardIssuer.detectNetwork("4123456789012"));
        Assert.assertEquals(null, cardIssuer.detectNetwork("41234567890123"));

        Assert.assertEquals("MasterCard", cardIssuer.detectNetwork("5112345678901234"));
        Assert.assertEquals(null, cardIssuer.detectNetwork("51123456789012345"));

        Assert.assertEquals(null, cardIssuer.detectNetwork("91123456789012345"));
    }

    @Test
    public void testOverlappedPrefixWithLengthLimit() {
        Assert.assertEquals("Maestro", cardIssuer.detectNetwork("6221261234567"));
    }
}