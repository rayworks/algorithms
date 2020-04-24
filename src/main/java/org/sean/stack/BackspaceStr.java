package org.sean.stack;

import java.util.Stack;

/***
 * 844. Backspace String Compare
 */
public class BackspaceStr {
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> stack = new Stack<>();
        String left = "";
        String right = "";

        StringBuilder builder = new StringBuilder();

        if (S != null) {
            parse(S, stack, builder);

            left = builder.toString();
        }

        builder = new StringBuilder();
        if(T != null) {
            parse(T, stack, builder);

            right = builder.toString();
        }

        return left.equals(right);

    }

    private void parse(String s, Stack<Character> stack, StringBuilder builder) {
        int cnt = s.length();
        for (int i = 0; i <cnt; i++) {
            char ch = s.charAt(i);
            if(ch == '#') {
                if(!stack.empty()) {
                    stack.pop();
                }
            }else {
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
    }
}
