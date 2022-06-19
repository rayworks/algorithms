package org.sean.trie;

/***
 * 745. Prefix and Suffix Search
 */
public class WordFilter {

    private final Trie trie;
    private String[] words;

    public WordFilter(String[] words) {
        trie = new Trie();
        this.words = words;

        // [suffix#prefix... ]
        //  consider "#test", "t#test", "st#test", "est#test", "test#test".
        for (int i = 0; i < words.length; i++) {
            String wd =  words[i];

            char[] chars = wd.toCharArray();
            int length = wd.length();
            for (int j = length - 1; j >= 0; j--) {
                trie.insert(new String(chars, j, length - j) + '{' + wd, i);
            }
        }
    }

    public int f(String prefix, String suffix) {
        return trie.startsWith(suffix + "{" + prefix);
    }

    // The customized Trie
    private static class Trie {

        private boolean endOfWord;

        static int childSize = 'z' - 'a' + 1 + 1; // a-z + '{'
        Trie[] children;

        private final char val;
        private final boolean isRoot;

        private int index;
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

        public void insert(String word, int index) {
            insertHelper(word, 0, index);
        }

        private void insertHelper(String word, int pos, int index) {
            if (pos >= word.length())
                return;

            char c = word.charAt(pos);

            int offset = c - 'a';
            if (children[offset] == null) {
                Trie te = new Trie(c, false);
                children[offset] = te;
                if (pos == word.length() - 1)
                    te.endOfWord = true;
            } else {
                if (pos == word.length() - 1)
                    children[offset].endOfWord = true;
            }
            children[offset].index = index;

            children[offset].insertHelper(word, pos + 1, index);
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
            if(child != null ) {
                if (child.endOfWord && pos == word.length() -1 )
                    return true;

                return child.search(word, pos + 1, wordMatch);
            } else {
                return false;
            }
        }

        public int startsWith(String prefix) {
            Trie p = this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch  = prefix.charAt(i);
                if(p.children[ch - 'a'] == null)
                    return -1;
                p = p.children[ch - 'a'];
            }
            return p.index;
        }
    }
}
