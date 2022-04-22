package org.sean.tree;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/***
 * 127. Word Ladder
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> dict = new HashSet<>(wordList);

        if (!dict.contains(endWord)) {
            return 0;
        }

        Deque<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int steps = 0;

        while (!queue.isEmpty()) {
            steps++;

            int size = queue.size();
            // traverse all the nodes on this level
            for (int m = 0; m < size; m++) {
                String word = queue.poll();

                for (int i = 0; i < word.length(); i++) {
                    for (int j = 0; j < 'z' - 'a' + 1; j++) {
                        char ch = (char) ('a' + j);
                        if (ch == word.charAt(i)) continue;

                        String nWord = word.substring(0, i) + ch + word.substring(i + 1);
                        if (nWord.equals(endWord)) {
                            return steps + 1;
                        }
                        if (dict.contains(nWord)) {
                            dict.remove(nWord); // remove the visited one to reduce the longer path

                            queue.add(nWord);
                        }
                    }
                }
            }

        }

        return 0;
    }
}
