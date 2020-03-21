package org.sean.array;

import java.util.HashSet;
import java.util.Set;

/***
 * 3. Longest Substring Without Repeating Characters
 */
public class LongestSubstrFinder {
    // "didf"
    // "bziuwnklhqzrxnb"
    // "qrsvbspk"
    public int lengthOfLongestSubstring0(String s) {
        int totalLen = 0;

        if (s.length() == 0)
            return 0;
        else if (s.length() == 1)
            return 1;

        int start = 0;
        int end = 0;

        HashSet<Character> set = new HashSet<>();

        int len = s.length();
        while (end < len) {
            while (end < len && !set.contains(s.charAt(end))) {
                set.add(s.charAt(end));
                ++end;
            }

            // found a duplicate one
            if (totalLen < set.size()) {
                totalLen = set.size();

                System.out.println(String.format(">>>size : %d | start : %d, end : %d", totalLen, start, end));
            }

            if (end < len) {
                for (int i = start; i < end; i++) {

                    // for next round, update the set range [start, curr]
                    if (set.contains(s.charAt(i)) && s.charAt(i) == s.charAt(end)) {
                        set.remove(s.charAt(i));

                        start = i + 1;
                        System.out.println(String.format(">>> start update: %d, end : %d", start, end));
                        break;
                    } else {
                        set.remove(s.charAt(i));
                    }
                }

                // continue with the new range
                set.add(s.charAt(end));

                ++end;
            }
        }

        return totalLen;
    }

    Set<Character> set = new HashSet<>();

    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        if (s.length() <= 1) return s.length();

        set.clear();

        int max = Integer.MIN_VALUE;

        int cnt = s.length();
        int i = 0;
        int j = i;
        while (i <= j && j < cnt) {
            char ch = s.charAt(j);

            if (set.contains(ch)) {
                set.remove(s.charAt(i++));
            } else {
                set.add(ch);
                if (max < j - i + 1) {
                    max = j - i + 1;
                }
                ++j;
            }
        }

        return max;
    }
}
