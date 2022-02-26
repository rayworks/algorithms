package org.sean.backtracking;

import java.util.*;

/***
 * 140. Word Break II
 */
public class WordBreakII {
    private List<String> outputs = new ArrayList<>();

    private void lookupWords(
            String s, List<String> wordDict, int currIndex, LinkedList<String> words) {
        int len = s.length();
        if (currIndex >= len) {
            int totalLen = words.stream().mapToInt(String::length).sum();
            if (totalLen == len) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < words.size(); i++) {
                    builder.append(words.get(i));
                    if (i != words.size() - 1) builder.append(' ');
                }
                outputs.add(builder.toString());
            }
            return;
        }
        for (int j = currIndex + 1; j <= len; j++) {
            String sub = s.substring(currIndex, j);
            if (wordDict.contains(sub)) {
                words.add(sub);
                lookupWords(s, wordDict, j, words);
                words.removeLast();
            }
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return Collections.emptyList();

        if (s.length() == 1) {
            if (wordDict.contains(s)) return Collections.singletonList(s);
            else return Collections.emptyList();
        }

        lookupWords(s, wordDict, 0, new LinkedList<>());

        return outputs;
    }
}
