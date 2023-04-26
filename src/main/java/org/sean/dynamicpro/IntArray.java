package org.sean.dynamicpro;

/***
 * 1416. Restore The Array
 */
public class IntArray {

    public int numberOfArrays(String s, int k) {
        int mod = 1_000_000_007;

        int len = s.length();
        int[] res = new int[len + 1];
        res[len] = 1; // {}

        //
        // We can construct the solution from the right most non-zero digit, and expand from right to left.
        // if left digits could be formed as a valid number, then add up the pre-calculated subSet result on the right
        // side.
        //
        // e.g.1317 , k = 2000
        //
        // Index:
        // 3|                                       {7} : 1
        // 2|                              [1]{7}, [17] : 1 + 1
        // 1|                 [3]{17} + [31]{7} + [317] : 2 + 1 + 1
        // 0|   [1]{317} + [13]{17} + [131]{7} + [1317] : 4 + 2 + 1 + 1
        //
        for (int i = len - 1; i >= 0; i--) {
            int cnt = 0;

            if (s.charAt(i) == '0') continue;

            for (int j = i + 1; j < len; j++) {
                if (j - i < 10) { // range : 1 <= k <= 10^9
                    int num = Integer.parseInt(s.substring(i, j));
                    if (num > 0 && num <= k) {
                        cnt += res[j];
                        cnt %= mod;
                    }
                } else {
                    break;
                }
            }
            int digits = len - i;
            if (digits < 10) { // add the largest one if in the range
                int n = Integer.parseInt(s.substring(i));

                if (n > 0 && n <= k) {
                    cnt += 1;
                    cnt %= mod;
                }
            }

            res[i] = cnt;
        }
        // System.out.println(Arrays.toString(res));

        return res[0];
    }
}
