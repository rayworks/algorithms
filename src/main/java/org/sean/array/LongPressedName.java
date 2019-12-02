package org.sean.array;

import java.util.*;

/***
 * 925. Long Pressed Name
 */
public class LongPressedName {
    private void readString(String str, List<Character> outUniqueChars, List<Integer> outCntGroup) {
        if (str == null || str.isEmpty())
            return;

        int len = str.length();

        // The characters of name and typed are lowercase letters.
        char prev = 'A';
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (ch != prev) {
                outUniqueChars.add(ch);

                prev = ch;
                outCntGroup.add(1);
            } else {
                int count = outCntGroup.get(outCntGroup.size() - 1) + 1;
                outCntGroup.set(outCntGroup.size() - 1, count);
            }
        }
    }

    public boolean isLongPressedName(String name, String typed) {
        if (name == null || name.isEmpty())
            return true;

        List<Character> characters = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        readString(name, characters, list);

        List<Character> actualChars = new ArrayList<>();
        List<Integer> expectedList = new ArrayList<>();
        readString(typed, actualChars, expectedList);

        if (characters.equals(actualChars)) {
            int cnt = actualChars.size();
            for (int i = 0; i < cnt; i++) {
                if (expectedList.get(i) < list.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
