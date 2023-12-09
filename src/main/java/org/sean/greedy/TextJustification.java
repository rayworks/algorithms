package org.sean.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 68. Text Justification
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int cnt = words.length;
        int left = 0;
        int right = 0;

        List<String> output = new ArrayList<>();
        while (right < cnt) {
            int sz = words[left].length();
            if (sz == maxWidth) { // already fit the whole line?
                output.add(words[left]);
                right = left = left + 1;
                continue;
            }

            right = left + 1;
            while (right < cnt) {
                sz += words[right].length();
                if (sz + 1 <= maxWidth) {// one blank
                    sz++;
                    right++;
                } else {
                    break;
                }
            }
            StringBuilder builder = genStringBuilder(words, maxWidth, right, left);
            output.add(builder.toString());

            if (right < cnt) {
                left = right;
            }
        }

        refineLastStrIfNeeded(maxWidth, output);

        return output;
    }

    private static void refineLastStrIfNeeded(int maxWidth, List<String> output) {
        int outCnt = output.size();
        String last = output.get(outCnt - 1);
        String[] splits = last.split("\\s+");
        System.out.println(Arrays.toString(splits));

        if (splits.length > 1) {
            StringBuilder builder = new StringBuilder();
            int lineCharCnt = 0;
            for (int i = 0; i < splits.length; i++) {
                builder.append(splits[i]);
                lineCharCnt += splits[i].length();

                if (i != splits.length - 1) {
                    builder.append(div);
                    lineCharCnt++;
                }
            }
            if (lineCharCnt < maxWidth) {
                int dlt = maxWidth - lineCharCnt;
                while (dlt > 0) {
                    builder.append(div);
                    dlt--;
                }
            }
            output.set(outCnt - 1, builder.toString());
        }
    }

    private static final char div = ' ';

    private static StringBuilder genStringBuilder(String[] words, int maxWidth, int right, int left) {
        // add [left, right-1]
        StringBuilder builder = new StringBuilder();
        int subWordCnt = right - left;
        int blankCnt = subWordCnt - 1;
        if (blankCnt == 0) {
            blankCnt = 1;
        }

        int charSum = 0;
        for (int i = left; i < right; i++) {
            charSum += words[i].length();
        }
        int blanks = maxWidth - charSum;

        int perBlank = blanks / blankCnt;
        int dltBlank = blanks % blankCnt;

        for (int i = left; i < right; i++) {
            builder.append(words[i]);

            if (i != right - 1 || left == right - 1) { // not for the last one

                for (int j = 0; j < perBlank; j++) {
                    builder.append(div);
                }
                if (dltBlank > 0) {
                    builder.append(div);
                    dltBlank--;
                }
            }
        }
        return builder;
    }
}
