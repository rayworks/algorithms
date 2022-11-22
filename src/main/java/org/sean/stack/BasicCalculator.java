package org.sean.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/***
 * 224. Basic Calculator
 */
public class BasicCalculator {
    private int[] calculateHelper(String s, int start) {
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Character> stackOps = new ArrayDeque<>();
        int len = s.length();

        int i = start;

        out:
        while (i < len) {
            char ch = s.charAt(i);
            switch (ch) {
                case '(':
                    int[] pair = calculateHelper(s, i + 1); // interception
                    stack.push(pair[0]);
                    i = pair[1];

                    calc(stack, stackOps);

                    break;
                case ')':
                    break out;
                case '+':
                    stackOps.push(ch);
                    break;
                case '-':
                    stackOps.push(ch);
                    break;
                case ' ':
                    break;

                default: {
                    // num starts
                    StringBuilder sb = new StringBuilder();
                    char c = s.charAt(i);
                    while (c >= '0' && c <= '9') {
                        sb.append(c);

                        i++;
                        if (i < len) c = s.charAt(i);
                        else break;
                    }

                    i--;
                    String digits = sb.toString();
                    if (digits.isEmpty()) {
                        break out;
                    }
                    int num = Integer.parseInt(digits);
                    //System.out.println(">< num extracted : " + num);
                    stack.push(num);

                    calc(stack, stackOps);
                }
                break;
            }

            i++;
        }

        while (!stackOps.isEmpty()) {
            int op = stackOps.pop();
            int curr = stack.pop();
            if (op == '-' && stack.isEmpty()) {
                return new int[]{-curr, i};
            }
            int prev = stack.pop();
            if (op == '-')
                stack.push(prev - curr);
            else if (op == '+')
                stack.push(prev + curr);
        }

        return new int[]{stack.pop(), i};
    }

    private void calc(Deque<Integer> stack, Deque<Character> stackOps) {
        char op;
        while (!stackOps.isEmpty() && (op = stackOps.pop()) != '(') {
            int curr = stack.pop();
            if (stack.isEmpty() && stackOps.isEmpty() && op == '-') {
                stack.push(-1 * curr);
                break;
            }

            if (!stack.isEmpty()) {
                int prev = stack.pop();
                if (op == '-')
                    stack.push(prev - curr);
                else
                    stack.push(prev + curr);
            }
        }
    }

    public int calculate(String s) {
        return calculateHelper(s, 0)[0];
    }
}
