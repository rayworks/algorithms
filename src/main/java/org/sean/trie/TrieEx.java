package org.sean.trie;

import java.util.ArrayList;
import java.util.List;

// 211. Add and Search Word - Data structure design
public class TrieEx {
    public List<TrieEx> children;

    // isEndOfWord is true if the node represents end of a word
    boolean isEndOfWord;

    public boolean isRoot;

    public Character val;

    public TrieEx() {
        this(true, null);
    }

    public TrieEx(boolean isRoot, Character t) {
        this.isRoot = isRoot;
        this.val = t;

        children = new ArrayList<>();
    }

    private void insert(TrieEx node, String str, int pos) {
        if (pos >= str.length()) {
            node.isEndOfWord = true;
            return;
        }

        boolean found = false;
        for (TrieEx tn : node.children) {
            if (tn.val.equals(str.charAt(pos))) {
                found = true;
                insert(tn, str, pos + 1);
                break;
            }
        }
        if (!found) {
            TrieEx n = new TrieEx(false, str.charAt(pos));
            node.children.add(n);

            insert(n, str, pos + 1);
        }
    }

    public void addWord(String word) {
        insert(this, word, 0);
    }

    /***
     * Retrieves the longest prefix of the given word from the trie.
     *
     * @param outPrefix the output {@link StringBuilder}
     * @param matches the output list of matched known words
     * @param word the word to retrieve the prefix from
     * @param pos the current position in the word
     */
    public void lookupPrefix(StringBuilder outPrefix, List<String> matches, String word, int pos) {
        if (pos >= word.length()) {
            return;
        }

        char ch = word.charAt(pos);
        if (!isRoot) {
            if (val == ch) {
                outPrefix.append(val);
                if (isEndOfWord) {
                    matches.add(outPrefix.toString());
                }

                for (TrieEx tn : children) {
                    tn.lookupPrefix(outPrefix, matches, word, pos + 1);
                }
            }
        } else {
            for (TrieEx tn : children) {
                tn.lookupPrefix(outPrefix, matches, word, pos);
            }
        }
    }

    private boolean search(String word, int pos, boolean matchedStrictly) {
        if (pos >= word.length()) {
            return !matchedStrictly || isEndOfWord;
        }

        for (TrieEx tn : children) {
            char val = tn.val;
            char target = word.charAt(pos);
            if (target == '.' || val == target) {
                boolean found = tn.search(word, pos + 1, matchedStrictly);
                if (found) return true;
            }
        }
        return false;
    }

    public boolean searchPrefix(String prefix) {
        return search(prefix, 0, false);
    }

    public boolean search(String word) {
        return search(word, 0, true);
    }
}
