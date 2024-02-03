package org.sean.dynamicpro;

/***
 * 1043. Partition Array for Maximum Sum
 */
public class PartitionArray {

    public int maxSumAfterPartitioning(int[] arr, int k) {
        int len = arr.length;
        int[] sums = new int[len];
        int maxSofar = -1;
        for (int i = 0; i < k; i++) {
            maxSofar = Math.max(arr[i], maxSofar);
            sums[i] = (i + 1) * maxSofar;
        }

        for (int j = k; j < len; j++) {
            int max = -1;

            // think about the formed group end position at j
            // xxx {j}
            // xx {xj}
            // ...
            for (int t = 0; t < k; t++) {
                max = Math.max(max, arr[j - t]);
                sums[j] = Math.max(sums[j], max * (t + 1) + sums[j - 1 - t]);
            }
        }
        return sums[len - 1];
    }
}
