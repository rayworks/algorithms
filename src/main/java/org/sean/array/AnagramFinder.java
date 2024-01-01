package org.sean.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/***
 * 438. Find All Anagrams in a String
 */
public class AnagramFinder {
    //#region sliding window
    public List<Integer> findAnagrams(String s, String p) {
        int patLen = p.length();
        int len = s.length();

        if (patLen > len)
            return Collections.emptyList();

        int[] actual = new int['z' - 'a' + 1];
        for (int i = 0; i < patLen; i++) {
            char ch = p.charAt(i);
            actual[ch - 'a'] += 1;
        }

        //  xxx$^$$^
        //   x$^
        List<Integer> out = new ArrayList<>();
        int start = 0;
        int end = start;

        int[] arr = new int['z' - 'a' + 1];
        while (end < len) {
            char ch = s.charAt(end);
            int index = ch - 'a';

            if (actual[index] > 0) { // has the char in pattern
                arr[index] += 1;

                if (arr[index] <= actual[index]) {
                    if (Arrays.equals(arr, actual)) {
                        out.add(start);
                    }
                } else {
                    // already have the max num of ch, try to remove the oldest one
                    while (start < end) {
                        char last = s.charAt(start);
                        arr[last - 'a'] -= 1;
                        if (last == ch) {
                            start++;
                            break;
                        }
                        start++;
                    }

                    if (Arrays.equals(arr, actual)) {
                        out.add(start);
                    }
                }

                end++;
            } else {
                // reset as it's not continual
                end++;
                start = end;
                Arrays.fill(arr, 0);
            }
        }

        return out;
    }
    //#endregion

    // O(N)
    public List<Integer> findAnagrams0(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length())
            return res;

        int[] pattern = new int[26];
        int[] cache = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pattern[p.charAt(i) - 'a'] += 1;
            cache[s.charAt(i) - 'a'] += 1;
        }

        if (Arrays.equals(cache, pattern))
            res.add(0);

        // form the subarray with fixed size
        for (int i = 0, j = p.length(); j < s.length(); i++, j++) {
            cache[s.charAt(j) - 'a'] += 1;
            cache[s.charAt(i) - 'a'] -= 1;

            if (Arrays.equals(cache, pattern)) {
                res.add(i + 1);
            }
        }

        return res;
    }
}
