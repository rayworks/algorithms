package org.sean.trie;

/***
 * 208. Implement Trie (Prefix Tree)
 */
public class Trie {
    private boolean endOfWord;

    static int childSize = 'z' - 'a' + 1; // a-z
    Trie[] children;

    private final char val;
    private final boolean isRoot;

    public char getVal() {
        return val;
    }

    public boolean isEndOfWord() {
        return endOfWord;
    }

    public Trie() {
        children = new Trie[childSize];
        this.isRoot = true;
        val = ' ';
    }

    public Trie(char ch, boolean isRoot) {
        children = new Trie[childSize];
        this.isRoot = isRoot;
        val = ch;
    }

    public void insert(String word) {
        insert(word, 0);
    }

    private void insert(String word, int pos) {
        if (pos >= word.length())
            return;

        char c = word.charAt(pos);

        int index = c - 'a';
        if (children[index] == null) {
            Trie te = new Trie(c, false);
            children[index] = te;
            if (pos == word.length() - 1)
                te.endOfWord = true;
        } else {
            if (pos == word.length() - 1)
                children[index].endOfWord = true;
        }

        children[index].insert(word, pos + 1);
    }

    public boolean search(String word) {
        return search(word, 0, true);
    }

    private boolean search(String word, int pos, boolean wordMatch) {
        if (pos >= word.length()) {
            return !wordMatch;
        }

        char e = word.charAt(pos);
        Trie child = children[e - 'a'];
        if (child != null) {
            if (child.endOfWord && pos == word.length() - 1)
                return true;

            return child.search(word, pos + 1, wordMatch);
        } else {
            return false;
        }
    }

    public boolean startsWith(String prefix) {
        return search(prefix, 0, false);
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
