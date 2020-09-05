package org.sean.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 763. Partition Labels
 * <p>
 * Hint:
 * Try to greedily choose the smallest partition that includes the first letter.
 * If you have something like "abaccbdeffed", then you might need to add b.
 * You can use an map like "last['b'] = 5" to help you expand the width of your partition.
 * </p>
 */
public class LabelPartition {
    // Input: S = "ababcbaca defegde hijhklij"
    // Output: [9,7,8]
    public List<Integer> partitionLabels(String S) {
        if (S == null || S.length() == 0) return Arrays.asList(0);

        int[] mapIndex = new int['z' - 'a' + 1];
        int len = S.length();
        for (int i = 0; i < len; i++) {
            char ch = S.charAt(i);
            mapIndex[ch - 'a'] = i;
        }

        List<Integer> outList = new ArrayList<>();
        int maxCurr = 0;
        int i = 0;
        char ch;
        while (i < len) {
            ch = S.charAt(i);
            int range = getCharLastIndex(mapIndex, ch);

            int max = range;
            for (; i <= range; i++) {
                ch = S.charAt(i);
                int nextPos = getCharLastIndex(mapIndex, ch);
                max = Math.max(max, nextPos);
                range = max;
            }
            outList.add(range);
        }

        List<Integer> out = new ArrayList<>();
        int length = outList.size();
        for (int j = 0; j < length; j++) {
            if (j == 0) out.add(outList.get(j) + 1);
            else
                // minus the index before the start pos of next segment
                out.add(outList.get(j) - outList.get(j - 1));
        }
        return out;
    }

    private int getCharLastIndex(int[] mapIndex, char ch) {
        return mapIndex[ch - 'a'];
    }
}
