package org.sean.dynamicpro;

// 392. Is Subsequence
// Input: s = "abc", t = "ahbgdc"
// Output: true
public class SubsequenceChecker {
    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length()) return false;

        int lenS = s.length();
        int lenT = t.length();
        int[][] dp = new int[lenS + 1][lenT + 1];
        for (int i = 1; i <= lenS; i++) {
            for (int j = 1; j <= lenT; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[lenS][lenT] >= lenS;
    }
}
