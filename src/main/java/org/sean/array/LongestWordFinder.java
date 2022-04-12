package org.sean.array;

import java.util.List;

/***
 * 524. Longest Word in Dictionary through Deleting
 */
public class LongestWordFinder {
    // two pointers : O(m*n)
    private boolean match(String text, String pat) {
        if (text.length() < pat.length())
            return false;

        int i = 0;
        int cnt = 0;
        int startPos = 0;
        int len = text.length();
        while (i < pat.length()){
            char ch = pat.charAt(i);
            while (startPos < len) {
                if(text.charAt(startPos) == ch) {
                    break;
                }
                startPos++;
            }

            if(startPos == len) {
                // not found
                break;
            }
            startPos++;
            cnt++;
            i++;
        }
        return cnt == pat.length();
    }

    public String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((o1, o2) -> {
            if (o1.length() == o2.length())
                return o1.compareTo(o2);
            else
                return Integer.compare(o2.length(), o1.length());
        });

        for (String word : dictionary) {
            if(match(s, word))
                return word;
        }
        return "";
    }

    // LCS O(m*k*n) TLE
    private int lcs(String text, String pat) {
        int rowLen = text.length();
        int colLen = pat.length();
        int[][] dp = new int[rowLen+1][colLen + 1];

        for (int i = 1; i <= rowLen; i++) {
            for (int j = 1; j <= colLen; j++) {
                int adjacentMax = Math.max(dp[i][j - 1], dp[i - 1][j]);
                if (text.charAt(i-1) == pat.charAt(j-1)) {
                    dp[i][j] = Math.max(adjacentMax, dp[i - 1][j - 1] + 1);
                }else {
                    dp[i][j] = adjacentMax;
                }
            }
        }
        return dp[rowLen][colLen];
    }

    public String findLongestWord0(String s, List<String> dictionary) {
        dictionary.sort((o1, o2) -> {
            if (o1.length() == o2.length())
                return o1.compareTo(o2);
            else
                return Integer.compare(o2.length(), o1.length());
        });

        for (String word : dictionary) {
            int lcsLen = lcs(s, word);
            if(lcsLen == word.length())
                return word;
        }
        return "";
    }
}
