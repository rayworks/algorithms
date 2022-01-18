package org.sean.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * 1079. Letter Tile Possibilities
 */
public class LetterTile {
    List<String> generate(List<String> words, String ch) {
        Set<String> set = new HashSet<>(words);
        set.add(ch);

        for (String word : words) {
            set.add(word + ch);
            set.add(ch + word);
            for (int j = 1; j < word.length(); j++) {
                set.add(word.substring(0, j) + ch + word.substring(j));
            }
        }
        return new ArrayList<>(set);
    }

    public int numTilePossibilities(String tiles) {
        List<String> outWords = new ArrayList<>();
        for (int i = 0; i < tiles.length(); i++) {
            String ch = tiles.charAt(i) + "";
            outWords = generate(outWords, ch);
        }
        return outWords.size();
    }
}
