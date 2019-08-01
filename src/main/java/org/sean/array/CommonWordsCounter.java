package org.sean.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/***
 * 819. Most Common Word
 */
public class CommonWordsCounter {
    // Input:
    // paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
    // banned = ["hit"]
    // Output: "ball"

    // paragraph only consists of letters, spaces, or the punctuation symbols !?',;.

    // answer is unique, and written in lowercase
    public String mostCommonWord(String paragraph, java.lang.String[] banned) {
        if(paragraph == null || paragraph.isEmpty())
            return "";
        paragraph = paragraph.toLowerCase();
        paragraph = paragraph.replaceAll("\\p{Punct}"," ");
        String[] strings = paragraph.split("\\s+");

        Set<String> set = new HashSet<>(Arrays.asList(banned));
        HashMap<String, Integer> map = new HashMap<>();
        int len = strings.length;
        for (int i = 0; i < len; i++) {
            if(!set.contains(strings[i])) {
                if(map.containsKey(strings[i])) {
                    map.put(strings[i], map.get(strings[i])+ 1);
                }else {
                    map.put(strings[i], 1);
                }
            }
        }
        int maxCnt = 0;
        for (Integer cnt : map.values()) {
            if(cnt > maxCnt) {
                maxCnt = cnt;
            }
        }

        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            if(map.get(key) == maxCnt) {
                return key;
            }
        }

        return "";
    }

    public static void main(String[] args) {
        CommonWordsCounter counter = new CommonWordsCounter();
        // "a, a, a, a, b,b,b,c, c"
        // ["a"]
        String s =
                counter.mostCommonWord(
                        "a, a, a, a, b,b,b,c, c",
                        new String[] {"a"});
        System.out.println(s);
    }
}
