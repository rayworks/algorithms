package org.sean.array;

import java.util.*;

/** * 22. Generate Parentheses */
public class ParenthesesGenerator {
    private static final String pair = "()";
    private Set<String> set = new HashSet<>();

    public List<String> generateParenthesis(int n) {
        if (n == 0) return Collections.emptyList();

        if (n == 1) return Arrays.asList(pair);

        return reduce(pair, generateParenthesis(n - 1));
    }

    private List<String> reduce(String s, List<String> strings) {
        int size = strings.size();

        set.clear();
        for (int i = 0; i < size; i++) { // e.g [ ()(), (()) ]
            String elem = strings.get(i);

            set.add(elem + s);

            int len = elem.length();
            for (int j = 0; j < len; j++) {
                set.add(elem.substring(0, j) + s + elem.substring(j));
            }
        }

        return new LinkedList<>(set);
    }
}
