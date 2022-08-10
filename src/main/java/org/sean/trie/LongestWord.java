package org.sean.trie;

import java.util.Arrays;

/***
 * 720. Longest Word in Dictionary
 */
public class LongestWord {
    class Trie {
        char val;
        Trie[] children = new Trie['z'-'a'+1];

        private boolean isRoot;
        private boolean endOfWord;
        public Trie() {
            isRoot = true;
            val = ' ';
        }
        public Trie(char ch) {
            isRoot = false;
            val = ch;
        }

        public void addWord(String word) {
            build(word, 0);
        }

        private void build(String word, int pos) {
            if (pos >= word.length()) {
                endOfWord = true;
                return;
            }
            char ch = word.charAt(pos);
            int index = ch - 'a';
            if (children[index] == null) {
                children[index] = new Trie(ch);
            }
            children[index].build(word, pos + 1);
        }

        public boolean searchFullWord(String word, int pos) {
            if (pos >= word.length()) {
                return endOfWord;
            }
            int ch = word.charAt(pos);

            if (children[ch - 'a'].val == word.charAt(pos) && children[ch - 'a'].endOfWord) {
                return children[ch - 'a'].searchFullWord(word, pos + 1);
            }
            return false;
        }
    }

    public String longestWord(String[] words) {
        Trie trie = new Trie();

        Arrays.sort(words, (o1, o2) -> {
            if (o1.length() == o2.length())
                return o1.compareTo(o2);
            return o1.length() - o2.length();
        });

        for (String wd: words) {
            trie.addWord(wd);
        }

        int maxLen = 0;
        String out = "";
        for (String str : words) {
            boolean matched = trie.searchFullWord(str, 0);
            if (matched) {
                if (maxLen < str.length()) {
                    maxLen = str.length();
                    out = str;
                }
            }
        }
        return out;

    }
}
