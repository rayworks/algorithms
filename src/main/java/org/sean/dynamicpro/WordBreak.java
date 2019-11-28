package org.sean.dynamicpro;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/***
 * 139. Word Break
 */
public class WordBreak {
    private LinkedList<Integer> matchedPositions = new LinkedList<>();

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.isEmpty()) return false;
        if (wordDict == null || wordDict.isEmpty()) return false;

        Set<String> map = new HashSet<>(wordDict);

        int len = s.length();
        boolean[] matched = new boolean[len];
        StringBuilder builder = new StringBuilder();

        boolean found = false;
        for (int i = 0; i < len; i++) {
            builder.append(s.charAt(i));
            String sub = builder.toString();

            found = false;
            if (map.contains(sub)) {
                found = true;
                matchedPositions.add(i);
            } else {
                // S(n) = S(0,i) + S(i + 1, n)
                // calculation based on previous matched points
                int preRecords = matchedPositions.size();
                for (int j = 0; j < preRecords; j++) {
                    int pos = matchedPositions.get(j);

                    String rest = s.substring(pos + 1, i + 1);
                    if (map.contains(rest)) {
                        matchedPositions.add(i);
                        found = true;
                        break;
                    }
                }
            }

            matched[i] = found;
        }

        return matched[len - 1];
    }
}
