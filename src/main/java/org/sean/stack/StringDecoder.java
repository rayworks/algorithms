package org.sean.stack;

import java.util.Stack;

/***
 * 394. Decode String
 */
public class StringDecoder {
    private Stack<String> stack = new Stack<>();

    // stack ops involve digits and chars
    // * [ -> digit : keep pushing in
    // * ] -> strs = chars * digit, push(strs)
    // * char -> pushed in
    public String decodeString(String s) {
        int len = s.length();
        int i = 0;
        StringBuilder numBuilder = new StringBuilder();
        while (i < len) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                numBuilder.append(ch);
            } else if (ch == '[') {
                if (numBuilder.length() > 0) {
                    // int multipler = Integer.parseInt(numBuilder.toString());
                    stack.push(numBuilder.toString());

                    numBuilder = new StringBuilder();
                }
            } else if (ch == ']') {
                StringBuilder builder = new StringBuilder();
                String str = stack.pop();
                int n = Integer.parseInt(stack.pop());

                for (int j = 0; j < n; j++) {
                    builder.append(str);
                }
                if (!stack.isEmpty()) {
                    String top = stack.peek();
                    if (Character.isLetter(top.charAt(0))) {
                        stack.pop();
                        stack.push(builder.insert(0, top).toString());
                    } else {
                        stack.push(builder.toString());
                    }
                } else {
                    stack.push(builder.toString());
                }

            } else { // letter
                if (!stack.isEmpty()) {
                    String top = stack.peek();
                    if (Character.isDigit(top.substring(0, 1).charAt(0))) {
                        stack.push(ch + "");
                    } else {
                        stack.pop();
                        stack.push(top + ch);
                    }
                } else {
                    stack.push(ch + "");
                }
            }
            ++i;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}
