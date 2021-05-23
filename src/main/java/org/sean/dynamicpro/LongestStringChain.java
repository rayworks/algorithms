package org.sean.dynamicpro;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 1048. Longest String Chain
public class LongestStringChain {
    public int longestStrChain(String[] words) {
        if (words == null || words.length == 0)
            return 0;

        int len = words.length;
        int[] dps = new int[len];
        Arrays.fill(dps, 1);

        Arrays.sort(words, (o1, o2) -> Integer.compare(o1.length(), o2.length()));

        int max = Integer.MIN_VALUE;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (isPredecessor(words[i], words[j])) {
                    if (dps[i] < dps[j] + 1) {
                        dps[i] = dps[j] + 1;
                    }
                }
            }

            if (max < dps[i]) {
                max = dps[i];
            }
        }

        return max;
    }

    private boolean isPredecessor(String word, String pred) {
        if (word.length() - pred.length() == 1) {
            int size = pred.length();
            int pos = -1;
            for (int i = 0; i < size; i++) {
                if (word.charAt(i) != pred.charAt(i)) {
                    pos = i;
                    break;
                }
            }
            if (pos >= 0) {
                String org = word.substring(0, pos) + word.substring(pos + 1);
                return org.equals(pred);
            } else {
                return true;
            }
        }
        return false;
    }

    // public solutions
    // O(N*(logN+L^2))
    public int longestStrChain0(String[] words) {
        Map<String, Integer> dp = new HashMap<>();

        // Sorting the list in terms of the word length.
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        int longestWordSequenceLength = 1;

        for (String word : words) {
            int presentLength = 1;
            // Find all possible predecessors for the current word by removing one letter at a time.
            for (int i = 0; i < word.length(); i++) {
                StringBuilder temp = new StringBuilder(word);
                temp.deleteCharAt(i);
                String predecessor = temp.toString();
                int previousLength = dp.getOrDefault(predecessor, 0);
                presentLength = Math.max(presentLength, previousLength + 1);
            }
            dp.put(word, presentLength);
            longestWordSequenceLength = Math.max(longestWordSequenceLength, presentLength);
        }
        return longestWordSequenceLength;
    }
}
