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

        boolean exchangeable = coinExchanger.exchangeable(new int[]{5, 10}, 12);
        Assert.assertFalse(exchangeable);

        Assert.assertFalse(coinExchanger.exchangeable(new int[]{5, 10}, 14));
        Assert.assertTrue(coinExchanger.exchangeable(new int[]{5, 10}, 55));

        Assert.assertTrue(coinExchanger.exchangeableWithLimit(new int[]{1, 5, 10, 20}, 31));
        Assert.assertFalse(coinExchanger.exchangeableWithLimit(new int[]{1, 5, 10, 20}, 40));
    }
}
