package org.sean.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/***
 * 71. Simplify Path
 */
public class PathHelper {
    public String simplifyPath(String path) {
        if (path.equals("/"))
            return path;

        String[] segments = path.split("\\/+");

        Deque<String> stack = new ArrayDeque<>();
        for (String segment : segments) {
            if (segment.equals(".")) {
                // No-Op
            } else if (segment.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                if (!segment.isEmpty())
                    stack.push(segment);
            }
        }

        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.insert(0, '/');
            builder.insert(1, stack.pop());
        }

        if (builder.length() == 0) {
            builder.append('/');
        }

        return builder.toString();
    }
}
