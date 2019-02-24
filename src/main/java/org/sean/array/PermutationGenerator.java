package org.sean.array;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/***
 * A recursive way to generate all the Permutation for a word.
 *
 * // a : a
 * // ab : ab ba
 * // abc : c{ab, ba} -> cab acb abc | cba bca bac
 */
public class PermutationGenerator {

    public List<String> generate(String src, int startPos) {
        if(src == null || src.length() < 1) {
            return Collections.emptyList();
        }

        int length = src.length();
        if (length - 1 == startPos) {
            List<String> outputs = new LinkedList<>();
            outputs.add(src.charAt(startPos) + "");
            return outputs;
        } else {
            char ch = src.charAt(startPos);
            List<String> generated = generate(src, startPos + 1);
            return merge(ch, generated);
        }
    }

    private List<String> merge(char ch, List<String> generate) {
        List<String> outputs = new LinkedList<>();

        int len = generate.size();
        for (int i = 0; i < len; i++) {
            String item = generate.get(i);

            if (item.length() > 1) {
                outputs.add(ch + item);
                for (int k = 1; k < item.length(); k++) {
                    outputs.add(item.substring(0, k) + ch + item.substring(k));
                }
                outputs.add(item + ch);
            } else {
                outputs.add(ch + item);
                outputs.add(item + ch);
            }

        }
        return outputs;
    }
}
