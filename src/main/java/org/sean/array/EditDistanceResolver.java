package org.sean.array;

import java.util.*;

public class EditDistanceResolver {
    private Set<String> getReducedStrings(String str) {
        if (str == null || str.isEmpty()) {
            return Collections.emptySet();
        } else {
            Set<String> set = new HashSet<>();
            int len = str.length();

            StringBuilder stringBuilder = new StringBuilder();

            // slice the list [:]
            for (int i = 0; i < len; i++) {
                if (i == 0) {
                    set.add(str.substring(1));
                } else if (i == len - 1) {
                    set.add(str.substring(0, i));
                } else {
                    String subStr = stringBuilder.append(str, 0, i)
                            .append(str, i + 1, len)
                            .toString();
                    set.add(subStr);

                    stringBuilder.delete(0, subStr.length());
                }
            }
            return set;
        }
    }

    // TO be refined
    public boolean isOneEditAway(String lhv, String rhv) {
        boolean oneWay = false;
        if (lhv.equals(rhv)) {
            oneWay = true;
        } else {
            int lenLeft = lhv.length();
            int lenRight = rhv.length();

            if (Math.abs(lenLeft - lenRight) > 1) {
                return false;
            }

            if (lenLeft == lenRight) {
                int unEqualCnt = 0;
                for (int k = 0; k < lenLeft; k++) {
                    if (lhv.charAt(k) != rhv.charAt(k)) {
                        unEqualCnt++;
                    }
                }
                // one mutation
                oneWay = unEqualCnt == 1;
            } else {
                // TODO: reduce the space usage in solution2
                Set<String> reducedStrings;
                if (lenLeft > lenRight) {
                    reducedStrings = getReducedStrings(lhv);
                    oneWay = reducedStrings.contains(rhv);
                } else {
                    reducedStrings = getReducedStrings(rhv);
                    oneWay = reducedStrings.contains(lhv);
                }
            }

        }

        return oneWay;
    }
}
