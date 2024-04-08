package org.sean.array;

import java.util.Arrays;
import java.util.Stack;

/***
 * 678. Valid Parenthesis String
 */
public class ParenthesisValidator {
    // region Counting O(N)
    public boolean checkValidString(String s) {
        int minOp = 0;
        int maxOp = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') ++minOp;
            else --minOp;

            if (c != ')') ++maxOp;
            else --maxOp;

            if (maxOp < 0)
                return false;

            minOp = Math.max(0, minOp);
        }
        return minOp == 0;
    }
    // endregion

    // region DP (Top-Down) O(N^3)
    public boolean checkValidString1(String s) {
        // L[  XXX  ]R
        //  [LxR LxR]
        int len = s.length();
        caches = new int[len][len];
        for (int[] cacheResult : caches) {
            Arrays.fill(cacheResult, -1);
        }

        return checkValidStringHelper(s, 0, len - 1);
    }

    private int[][] caches;

    private boolean checkValidStringHelper(String s, int left, int right) {
        if (left > right) {// empty string ?!
            caches[left][right] = 1;
            return true;
        }

        if (caches[left][right] >= 0)
            return caches[left][right] == 1;

        char leftCh = s.charAt(left);
        char rightCh = s.charAt(right);
        if (left == right) {
            caches[left][right] = leftCh == '*' ? 1 : 0;
            return caches[left][right] == 1;
        }

        // // x[xxx]x
        if ((leftCh == '(' || leftCh == '*')
                && (rightCh == ')' || rightCh == '*')
                && checkValidStringHelper(s, left + 1, right - 1)) {

            caches[left][right] = 1;
            return true;
        }

        for (int i = left; i < right; i++) {
            if (checkValidStringHelper(s, left, i) && checkValidStringHelper(s, i + 1, right)) {
                caches[left][right] = 1;
                return true;
            }
        }

        caches[left][right] = 0;
        return false;
    }
    // endregion

    // region Double Stacks O(N)
    public boolean checkValidString0(String s) {
        Stack<Integer> stackLeft = new Stack<>();
        Stack<Integer> stackStar = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stackLeft.push(i);
            } else if (c == '*') {
                stackStar.push(i);
            } else {
                // balance ')'
                if (!stackLeft.empty()) {
                    stackLeft.pop();
                } else if (!stackStar.empty()) {
                    stackStar.pop();
                } else {
                    return false;
                }
            }

        }
        while (!stackLeft.empty() && !stackStar.empty()) {
            if (stackLeft.pop() > stackStar.pop()) { // any '*' before '(' ?
                return false;
            }
        }
        return stackLeft.empty();
    }
    // endregion
}
