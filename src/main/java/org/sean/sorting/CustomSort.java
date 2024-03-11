package org.sean.sorting;

// 791. Custom Sort String
public class CustomSort {
    public String customSortString(String order, String s) {
        if (s.length() == 1)
            return s;

        int[] charCnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charCnt[s.charAt(i) - 'a'] += 1;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < order.length(); i++) {
            char ch = order.charAt(i);
            int cnt = charCnt[ch - 'a'];
            if (cnt > 0) {
                for (int j = 0; j < cnt; j++) {
                    builder.append(ch);
                }

                charCnt[ch - 'a'] = 0;
            }
        }
        for (int i = 0; i < charCnt.length; i++) {
            char ch = (char) ('a' + i);
            int cnt = charCnt[i];
            if (cnt > 0) {
                for (int j = 0; j < cnt; j++) {
                    builder.append(ch);
                }
            }
        }

        return builder.toString();
    }
}
