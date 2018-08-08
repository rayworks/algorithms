package org.sean.array;

public class Max2Finder {
    public int[] getMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        int[] result = new int[2];

        for (int i : nums) {
            if (i > secondMax) {
                secondMax = i;

                if (secondMax > max) {
                    int tmp = max;
                    max = secondMax;
                    secondMax = tmp;
                }
            }
        }

        result[0] = max;
        result[1] = secondMax;

        return result;
    }
}
