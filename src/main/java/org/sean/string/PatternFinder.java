package org.sean.string;

import java.util.*;

/***
 * 890. Find and Replace Pattern
 */
public class PatternFinder {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        if (words == null || pattern == null)
            return Collections.emptyList();

        int len = pattern.length();
        String patternList = filterPattern(pattern, len);

        List<String> result = new LinkedList<>();
        for (String wd : words) {
            if (patternList.equals(filterPattern(wd, wd.length()))) {
                result.add(wd);
            }
        }

        return result;
    }

    private String filterPattern(String pattern, int len) {
        StringBuilder patternList = new StringBuilder();
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0; j < len; j++) {
            char key = pattern.charAt(j);
            if (!map.containsKey(key)) {
                map.put(key, j);
            }
        }

        int lastCnt = 1;
        char lastChar = pattern.charAt(0);
        for (int i = 1; i < len; i++) {
            char ch = pattern.charAt(i);
            if (ch != lastChar) {
                // encoded as 0x1y2z
                patternList.append(map.get(lastChar)).append(lastCnt);
                lastCnt = 1;
                lastChar = ch;
            } else {
                ++lastCnt;
            }
        }
        // append the last
        patternList.append(map.get(lastChar)).append(lastCnt);
        return patternList.toString();
    }
}
