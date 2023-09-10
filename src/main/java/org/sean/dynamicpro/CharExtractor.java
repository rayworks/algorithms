package org.sean.dynamicpro;

import java.util.Arrays;
import java.util.HashSet;

/***
 * 2707. Extra Characters in a String
 */
public class CharExtractor {
    public int minExtraChar(String s, String[] dictionary) {
        int len = s.length();
        int[] dp = new int[len]; // dp[i] : the max subSeq num at pos i

        HashSet<String> set = new HashSet<>();
        set.addAll(Arrays.asList(dictionary));

        dp[0] = set.contains(String.valueOf(s.charAt(0))) ? 1 : 0;
        for (int i = 1; i < len; i++) {
            // xi
            // xxx i
            // xx xi
            int max = dp[i - 1];
            String curr = String.valueOf(s.charAt(i));
            if (set.contains(curr)) {
                max = Math.max(max, 1 + dp[i - 1]);
            }
            for (int j = i - 1; j >= 0; j--) {
                String sub = s.substring(j, i + 1);
                if (set.contains(sub)) {
                    int start = j != 0 ? dp[j - 1] : 0;
                    max = Math.max(max, start + sub.length());
                }
            }
            dp[i] = max;
        }
        // System.out.println(Arrays.toString(dp));

        return len - dp[len - 1];
    }
}
