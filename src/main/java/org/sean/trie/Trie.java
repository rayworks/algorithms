package org.sean.trie;

import java.util.ArrayList;
import java.util.List;

// 211. Add and Search Word - Data structure design
public class Trie {
    public List<Trie> children;

    // isEndOfWord is true if the node represents end of a word
    boolean isEndOfWord;

    public boolean isRoot;

    public Character val;

    public Trie() {
        this(true, null);
    }

    public Trie(boolean isRoot, Character t) {
        this.isRoot = isRoot;
        this.val = t;

        children = new ArrayList<>();
    }

    private void insert(Trie node, String str, int pos) {
        if (pos >= str.length()) {
            node.isEndOfWord = true;
            return;
        }

        boolean found = false;
        for (Trie tn : node.children) {
            if (tn.val.equals(str.charAt(pos))) {
                found = true;
                insert(tn, str, pos + 1);
                break;
            }
        }
        if (!found) {
            Trie n = new Trie(false, str.charAt(pos));
            node.children.add(n);

            insert(n, str, pos + 1);
        }
    }

    public void addWord(String word) {
        insert(this, word, 0);
    }

    private boolean search(String word, int pos) {
        if (pos >= word.length()) {
            return isEndOfWord;
        }

        for (Trie tn : children) {
            char val = tn.val;
            char target = word.charAt(pos);
            if (target == '.' || val == target) {
                boolean found = tn.search(word, pos + 1);
                if (found) return true;
            }
        }
        return false;
    }

    public boolean search(String word) {
        return search(word, 0);
    }
}
