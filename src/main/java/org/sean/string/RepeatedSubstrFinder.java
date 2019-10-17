package org.sean.string;

import java.util.Arrays;

/** * 459. Repeated Substring Pattern */
public class RepeatedSubstrFinder {

    // Solution 1: If the substring is repeated, it must be in the range [0, length/2]
    public boolean repeatedSubstringPattern0(String s) {
        if (s == null || s.length() <= 1) {
            return false;
        }
        int length = s.length();
        int range = length / 2;

        System.out.println(Arrays.toString(prefixFunction(s)));

        for (int i = 1; i <= range; i++) {
            String preStr = s.substring(0, i);
            String post = s.substring(i);

            String dup = post + preStr;
            if (dup.equals(s)) {
                return true;
            }
        }
        return false;
    }

    // Solution 2: Use the pre-calculated prefix function.
    //
    // https://cp-algorithms.com/string/prefix-function.html
    public boolean repeatedSubstringPattern1(String s) {
        if (s == null || s.length() <= 1) {
            return false;
        }

        int[] prefixes = prefixFunction(s);
        System.out.println(Arrays.toString(prefixes));

        int length = s.length();
        int lastPrefixCnt = prefixes[length - 1];
        int k = length - lastPrefixCnt;

        return lastPrefixCnt > 0 && length % k == 0;
    }

    // prefix function
    private int[] prefixFunction(String s) {
        int n = s.length();
        int[] pi = new int[n];

        for (int i = 1; i < n; i++) {
            int j = pi[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j)) j = pi[j - 1];

            if (s.charAt(i) == s.charAt(j)) j++;
            pi[i] = j;
        }
        return pi;
    }

    // Solution 3: Concate the rotated string
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() <= 1) {
            return false;
        }

        String ss = s + s;
        return ss.substring(1, ss.length() - 1).contains(s);
    }
}
