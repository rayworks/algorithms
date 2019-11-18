package org.sean.array;

/** * 680. Valid Palindrome II */
public class PalindromeValidator {
    private boolean valid(String s, int l, int r, int removedCnt) {
        if (removedCnt > 1) return false;

        char left = s.charAt(l);
        char right = s.charAt(r);

        if (l < r) {
            if (l == r - 1) {
                if (removedCnt == 0) return true;
                else return left == right;
            } else {
                if (left == right) {
                    return valid(s, l + 1, r - 1, removedCnt);
                } else {
                    if (s.charAt(l + 1) == right
                            && s.charAt(r - 1) == left) { // it could go either direction
                        return valid(s, l + 1, r, removedCnt + 1)
                                || valid(s, l, r - 1, removedCnt + 1);
                    } else {
                        if (s.charAt(l + 1) == right) {
                            return valid(s, l + 1, r, removedCnt + 1);
                        } else if (s.charAt(r - 1) == left) {
                            return valid(s, l, r - 1, removedCnt + 1);
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean validPalindrome(String s) {
        return valid(s, 0, s.length() - 1, 0);
    }
}
