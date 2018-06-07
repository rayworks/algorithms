package org.sean.dynamicpro;

public class RodCutting {
    //length:       1, 2, 3, 4, 5,  6,  7,  8,  9,  10
    int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

    public int getPriceForLength(int n) {
        if (n == 0)
            return 0;

        int q = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            q = Math.max(q, prices[i - 1] + getPriceForLength(n - i));
        }

        return q;
    }

    public static void main(String[] args) {
        int n = 4;
        int result = new RodCutting().getPriceForLength(n);
        System.out.println(String.format("The result for %d is %d", n, result));
    }
}
