package org.sean.trie;

import java.util.ArrayList;
import java.util.List;

/***
 * 1268. Search Suggestions System
 */
public class SearchHelper {
    private Trie trie;

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        // Trie + DFS
        trie = new Trie();

        for (String prod : products) {
            trie.insert(prod);
        }

        ArrayList<List<String>> out = new ArrayList<>();
        for (int i = 0; i < searchWord.length(); i++) {
            out.add(new ArrayList<>());
        }
        lookupWords(trie, searchWord, 3, 0, out);

        return out;
    }

    private void lookupWords(Trie node, String prefix, int limit, int pos, List<List<String>> out) {
        if (pos >= prefix.length())
            return;

        char ch = prefix.charAt(pos);
        //Trie node = this;
        Trie next = node.children[ch - 'a'];

        List<String> candidatesPerPos = new ArrayList<>();
        if (next != null) {
            StringBuilder builder = new StringBuilder(prefix.substring(0, pos + 1));
            addWordsWithPrefix(next, builder, limit, candidatesPerPos);

            out.get(pos).addAll(candidatesPerPos);

            lookupWords(next, prefix, limit, pos + 1, out);
        }
        // else : break from here since no matched word found
    }

    private void addWordsWithPrefix(Trie trie, StringBuilder prev, int limit, List<String> outWords) {
        if (outWords.size() >= limit)
            return;

        if (trie.isEndOfWord()) {
            outWords.add(prev.toString());

            // check for deeper words
            if (trie.children.length == 0)
                return;
        }

        for (Trie t : trie.children) {
            if (t != null) {
                prev.append(t.getVal());
                int addedPos = prev.length() - 1;
                addWordsWithPrefix(t, prev, limit, outWords);
                prev.deleteCharAt(addedPos);

                if (outWords.size() >= limit)
                    break;
            }
        }
    }
}
