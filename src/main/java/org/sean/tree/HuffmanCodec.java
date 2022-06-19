package org.sean.tree;

import org.sean.trie.TrieEx;

import java.util.*;
import java.util.stream.Collectors;

public class HuffmanCodec {
    private static class Node implements Comparable<Node> {
        char ch;
        int frequency;

        Node left;
        Node right;

        public Node(char ch, int frequency) {
            this.ch = ch;
            this.frequency = frequency;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.frequency, o.frequency);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "ch=" + ch +
                    ", frequency=" + frequency +
                    '}';
        }
    }

    private final PriorityQueue<Node> pq = new PriorityQueue<>();
    private final Map<Character, String> codecMap = new HashMap<>();
    private final Map<String, Character> decodecMap = new HashMap<>();
    private Node root = null;

    private TrieEx trieEx;

    private void buildTree(String raw) {
        pq.clear();

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < raw.length(); i++) {
            char ch = raw.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // build a priority queue according to the frequency of the nodes
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        Node tmp = null;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
//            System.out.println("\n" + node);
            if (tmp == null) {
                tmp = node;
            } else {
                // add the new parent node
                Node p = new Node('X', node.frequency + tmp.frequency);
                p.left = tmp;
                p.right = node;
                pq.offer(p);

                tmp = null;
            }
        }
        this.root = tmp;

//        printTreeByLevel(tmp);

        buildCodecMap(map.keySet());

        buildTrie();
    }

    public String encode(String raw) {
        buildTree(raw);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < raw.length(); i++) {
            builder.append(codecMap.get(raw.charAt(i)));
        }
        return builder.toString();
    }

    public String decode(String encodedStr) {
        StringBuilder builderOut = new StringBuilder();

        int start = 0;
        int wordEnd;
        int end = encodedStr.length();
        while (start < end) {
            String sub = null;
            wordEnd = start + 1;
            while (wordEnd <= end) {
                sub = encodedStr.substring(start, wordEnd);
                if (encodedStrFound(sub))
                    break;

                wordEnd++;
            }
            builderOut.append(decodecMap.getOrDefault(sub, '\0'));

            start = wordEnd;
        }
        String out = builderOut.toString();
//        System.out.println("Result : " + out);
        return out;
    }

    private void printTreeByLevel(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        System.out.println(root.ch);

        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                Node poll = queue.poll();
                // System.out.printf(poll.toString() + " | ");

                if (poll.left != null)
                    queue.offer(poll.left);
                if (poll.right != null)
                    queue.offer(poll.right);
            }
//            System.out.println();
        }
    }


    private void buildTrie() {
        trieEx = new TrieEx();

        for (String code : decodecMap.keySet()) {
            trieEx.addWord(code);
        }
    }

    private boolean encodedStrFound(String encoded) {
        return trieEx.search(encoded);
    }

    private void buildCodecMap(Set<Character> chars) {
        traverseTree(root, chars, new LinkedList<>());

        /*for (Map.Entry<Character, String> e : codecMap.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }*/
    }

    private void traverseTree(Node node, Set<Character> chars, LinkedList<Character> path) {
        if (node != null) {
            path.add('0');
            traverseTree(node.left, chars, path);
            path.removeLast();

            if (node.left == null && node.right == null) {
                if (chars.contains(node.ch)) {
                    String encoded = path.stream().map(character -> character + "").collect(Collectors.joining());
                    codecMap.put(node.ch, encoded);
                    decodecMap.put(encoded, node.ch);
                }
            }

            path.add('1');
            traverseTree(node.right, chars, path);
            path.removeLast();
        }
    }
}
