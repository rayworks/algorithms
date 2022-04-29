package org.sean.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/***
 * 842. Split Array into Fibonacci Sequence
 */
public class FibonacciArraySplitter {
    public List<Integer> splitIntoFibonacci(String num) {
        if (num.length() < 3)
            return Collections.emptyList();

        out.clear();
        found = false;

        search(num, 0, new LinkedList<>());

        return out;
    }


    private List<Integer> out = new ArrayList<>();
    private boolean found;

    private void search(String num, int pos, LinkedList<String> fibs) {
        if (found) return;

        int len = num.length();
        for (int i = 1; i < len - pos + 1; i++) {
            if (pos + i > len)
                break;

            String sub = num.substring(pos, pos + i);
            if (sub.startsWith("0") && sub.length() > 1)
                break;

            // stops when it's out of the Integer range
            if (Long.parseLong(sub) > Integer.MAX_VALUE) {
                break;
            }

            if (fibs.size() < 2) {
                fibs.add(sub);
                search(num, pos + i, fibs);
                fibs.removeLast();
            } else {
                int last = Integer.parseInt(fibs.getLast());
                int preLast = Integer.parseInt(fibs.get(fibs.size() - 2));
                String next = String.valueOf(last + preLast);
                if (sub.equals(next)) {
                    fibs.add(sub);

                    if (pos + i >= len) {
                        found = true;
                        out = fibs.stream().map(Integer::parseInt).collect(Collectors.toList());
                        return;
                    }

                    search(num, pos + i, fibs);

                    fibs.removeLast();
                } else if (sub.length() >= next.length()) { // prune the branch as the next number can't be formed
                    return;
                }
            }
        }
    }
}
