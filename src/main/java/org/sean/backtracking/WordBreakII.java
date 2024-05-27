package org.sean.backtracking;

import java.util.*;

/***
 * 140. Word Break II
 */
public class WordBreakII {
    List<String> out = new ArrayList<>();

    private void lookupWords(String s, int pos, List<String> parts, int size, Set<String> set) {
        if (pos >= s.length()) {
            //int len = parts.stream().mapToInt(value -> value.length()).sum();
            if (size == s.length()) {
                out.add(String.join(" ", parts));
            }
            return;
        }

        for (int i = pos; i < s.length(); i++) {
            String word = s.substring(pos, i + 1);
            if (set.contains(word)) {
                parts.add(word);
                lookupWords(s, i + 1, parts, size + word.length(), set);
                parts.remove(parts.size() - 1);
            }
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return Collections.emptyList();

        //  all such possible sentences in any order.
        HashSet<String> dict = new HashSet<>(wordDict);
        //  catsanddog
        // cat{sanddog}
        // cats{anddog}
        // ...

        lookupWords(s, 0, new ArrayList<>(), 0, dict);

        return out;
    }
}
