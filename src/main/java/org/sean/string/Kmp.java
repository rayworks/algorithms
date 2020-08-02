package org.sean.string;

import java.util.Arrays;

public class Kmp {

    // Calculate the `next` (index) array for the substring;
    // For each p[i], find the largest suffix of P(i) that is equal to a prefix of P(i - 1)
    private int[] computeNext(String subArray) {
        int subSize = subArray.length();

        int[] next = new int[subSize];
        next[0] = -1;
        next[1] = 0;

        for (int i = 2; i < subSize; i++) {
            int j = next[i - 1] + 1;
            while (j > 0 && subArray.charAt(i - 1) != subArray.charAt(j - 1)) {
                j = next[j - 1] + 1;
            }
            next[i] = j;
        }
        return next;
    }

    /***
     * Find the first position of matched pattern in source
     * @param source
     * @param pattern
     * @return first matched position in source ; otherwise -1;
     */
    public int match(String source, String pattern) {
        int m = source.length();
        int n = pattern.length();
        if (m < n) return -1;

        int[] next = computeNext(pattern);
        System.out.println("Next array : " + Arrays.toString(next));

        int i = 0, j = 0;
        while (i < m) {
            if (pattern.charAt(j) == source.charAt(i)) {
                ++j;
                ++i;
            } else {
                if (j > 0) j = next[j - 1] + 1;
                if (j == 0) {
                    ++i;
                }
            }

            if (j == n) {
                return i - n;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Kmp kmp = new Kmp();
        int matchedPos = kmp.match("xyxxyxyxyyxyxyxyyxyxyxx", "xyxyyxyxyxx");
        System.out.println("Matched position : " + matchedPos);

        matchedPos = kmp.match("xyxxyxyxyyxy", "yxyyy");
        System.out.println("Matched position : " + matchedPos);

        matchedPos = kmp.match("ababcabcacbab", "abcac");
        System.out.println("Matched position : " + matchedPos);
    }
}
