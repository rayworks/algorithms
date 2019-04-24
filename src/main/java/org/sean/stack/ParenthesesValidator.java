package org.sean.stack;

import java.util.Stack;

// 20. Valid Parentheses
public class ParenthesesValidator {
    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }

            if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    Character outCh = stack.pop();
                    switch (ch) {
                        case ')':
                            if (outCh != '(')
                                return false;
                            break;
                        case '}':
                            if (outCh != '{')
                                return false;
                            break;
                        case ']':
                            if (outCh != '[')
                                return false;
                            break;
                    }
                }
            }
        }

        return stack.isEmpty();
    }
}
