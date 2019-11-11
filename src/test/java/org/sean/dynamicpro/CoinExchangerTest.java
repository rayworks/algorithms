package org.sean.dynamicpro;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoinExchangerTest {

    private CoinExchanger coinExchanger;

    @Before
    public void setup() {
        coinExchanger = new CoinExchanger();
    }

    @Test
    public void coinChange() {
        int count = coinExchanger.coinChange(new int[] {1, 2, 5}, 11);
        Assert.assertEquals(3, count);
    }
}
