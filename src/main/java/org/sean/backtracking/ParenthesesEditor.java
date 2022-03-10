package org.sean.backtracking;

import java.util.*;

/***
 *
 * 301. Remove Invalid Parentheses
 *
 * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the
 * input string valid. Return all the possible results. You may return the answer in any order.
 */
public class ParenthesesEditor {
    Set<String> output = new HashSet<>();

    static int max = Integer.MIN_VALUE;

    void backtrack(String s, int pos, StringBuilder builder, int leftCnt, int rightCnt) {
        int length = s.length();
        if (pos == length) {
            if (isValid(builder, leftCnt, rightCnt)) {
                if (max < builder.length()) {
                    max = builder.length();
                }
                if (builder.length() == max)
                    output.add(builder.toString());
            }
            return;
        }

        if (builder.length() + length - pos < max)
            return;


        boolean leftFound = false;
        boolean rightFound = false;
        char ch = s.charAt(pos);
        if (ch == '(') {
            leftCnt += 1;
            leftFound = true;
        } else if (ch == ')') {
            rightCnt += 1;
            rightFound = true;
        }
        builder.append(ch);
        backtrack(s, pos + 1, builder, leftCnt, rightCnt);

        if (leftFound) leftCnt -= 1;
        if (rightFound) rightCnt -= 1;
        builder.deleteCharAt(builder.length() - 1);
        backtrack(s, pos + 1, builder, leftCnt, rightCnt);
    }

    private boolean isValid(StringBuilder builder, int leftCnt, int rightCnt) {
        if (leftCnt != rightCnt)
            return false;

        if (leftCnt == 0) return true;

        int sum = 0;
        String str = builder.toString();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (sum < 0)
                return false;

            if (ch == '(') {
                sum += 1;
            } else if (ch == ')') {
                sum -= 1;
            }
        }
        return sum == 0;

    }

    public List<String> removeInvalidParentheses(String s) {
        // preCheck
        StringBuilder builder = new StringBuilder();
        boolean leftContains = s.contains("(");
        boolean rightContains = s.contains(")");
        if (leftContains && !rightContains || (!leftContains && rightContains)) {
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch != '(' && ch != ')')
                    builder.append(ch);
            }
            return Arrays.asList(builder.toString());
        }
        if (!leftContains && !rightContains) {
            return Arrays.asList(s);
        }

        max = Integer.MIN_VALUE;
        backtrack(s, 0, new StringBuilder(), 0, 0);

        ArrayList<String> out = new ArrayList<>();
        for (String str : output) {
            if (str.length() == max) {
                out.add(str);
            }
        }
        return out;
    }
}
