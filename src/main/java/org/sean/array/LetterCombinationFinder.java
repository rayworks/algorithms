package org.sean.array;

import java.util.*;

/***
 * 17. Letter Combinations of a Phone Number
 */
public class LetterCombinationFinder {
    static HashMap<Integer, List<String>> map = new HashMap<>();
    static {
        map.put(2, Arrays.asList("a", "b", "c"));
        map.put(3, Arrays.asList("d", "e", "f"));
        map.put(4, Arrays.asList("g", "h", "i"));
        map.put(5, Arrays.asList("j", "k", "l"));
        map.put(6, Arrays.asList("m", "n", "o"));
        map.put(7, Arrays.asList("p", "q", "r", "s"));
        map.put(8, Arrays.asList("t", "u", "v"));
        map.put(9, Arrays.asList("w", "x", "y", "z"));
    }

    List<String> getCombinations(String str, int index) {
        int digit = Integer.parseInt(str.charAt(index) + "");
        if(index == str.length() - 1) {
            return map.get(digit);
        }else {
            return merge(map.get(digit), getCombinations(str, ++index));
        }
    }

    private List<String> merge(List<String> letters, List<String> groups) {
        List<String> result = new LinkedList<>();
        for (String ch : letters) {
            for (String str : groups) {
                result.add(ch + str);
            }
        }
        return result;
    }

    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.isEmpty())
            return Collections.EMPTY_LIST;

        return getCombinations(digits, 0);
    }
}
