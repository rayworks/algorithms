package org.sean.array;

import java.util.Arrays;

/***
 * 1833. Maximum Ice Cream Bars
 */
public class IceCreamCounter {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);

        int max = 0;
        for (int i = 0; i < costs.length; i++) {
            if (coins >= costs[i]) {
                max++;
                coins -= costs[i];
            }else {
                break;
            }
        }
        return  max;
    }
}
