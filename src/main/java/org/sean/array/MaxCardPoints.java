package org.sean.array;

/***
 * 1423. Maximum Points You Can Obtain from Cards
 */
public class MaxCardPoints {

    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        if (k == 1) {
            return Math.max(cardPoints[0], cardPoints[len - 1]);
        }
        int total = 0;
        for (int e : cardPoints) {
            total += e;
        }
        if (k >= len)
            return total;

        // reverse thinking : find the subarray with the min sum
        // sliding window
        int winLen = len - k;
        int sum = 0;
        for (int j = 0; j < winLen; j++) {
            sum += cardPoints[j];
        }
        int min = sum;
        for (int i = 1; i + winLen <= len; i++) {
            sum -= cardPoints[i - 1];
            sum += cardPoints[i + winLen - 1];

            if (min > sum) {
                min = sum;
            }
        }
        return total - min;
    }
}
