package org.sean.trie;

import java.util.*;

//
// As part of a new payments system, you’ve been asked to implement some code to support showing the card issuer as the
// user enters their number. For example, before the user even clicks “Pay”, you want to display “VISA” for Visa cards,
// “MC” for MasterCard, etc. I’ve pasted in some basic rules and a test harness, so I’d like you to go ahead and
// implement the detection function for those rules.
//
// | Network          | IIN (Prefix)                                       | Length     |
// | Amex             | 34, 37                                             | 15         |
// | Diners           | 38, 39                                             | 14         |

// Part 2
// | Visa             | 4                                                  | 13, 16, 19 |
// | MasterCard       | 51, 52, 53, 54, 55                                 | 16         |

// Part 3
// | Discover         | 6011, 622126-622925, 644-649, 65                   | 16, 19     |
// | Maestro          | 50, 56-69                                          | 12-19      |
//
public class CardIssuer {
    // region solution 2 : Using Trie to retrieve the prefix efficiently
    private TrieEx trieEx;

    Map<String, String> prefixCard = new HashMap<>();
    Map<String, List<Integer>> cardLengths = new HashMap<>();

    private void init() {
        trieEx = new TrieEx();

        trieEx.addWord("34");
        trieEx.addWord("37");
        trieEx.addWord("38");
        trieEx.addWord("39");
        String[] strAmex = {"34", "37"};
        String[] strDiners = {"38", "39"};

        cardLengths.put("Amex", Arrays.asList(15));
        cardLengths.put("Diners", Arrays.asList(14));
        Arrays.stream(strAmex).forEach(s -> prefixCard.put(s, "Amex"));
        Arrays.stream(strDiners).forEach(s -> prefixCard.put(s, "Diners"));

        trieEx.addWord("4");
        prefixCard.put("4", "Visa");
        cardLengths.put("Visa", Arrays.asList(13, 16, 19));

        for (int i = 51; i < 56; i++) {
            String s = String.valueOf(i);
            trieEx.addWord(s);
            prefixCard.put(s, "MasterCard");
        }
        cardLengths.put("MasterCard", Arrays.asList(16));

        trieEx.addWord("6011");
        prefixCard.put("6011", "Discover");
        for (int j = 622126; j <= 622925; j++) {
            trieEx.addWord(String.valueOf(j));
            prefixCard.put(String.valueOf(j), "Discover");
        }
        for (int k = 644; k < 650; k++) {
            trieEx.addWord(String.valueOf(k));
            prefixCard.put(String.valueOf(k), "Discover");
        }
        trieEx.addWord("65");
        prefixCard.put("65", "Discover");
        cardLengths.put("Discover", Arrays.asList(16, 19));

        trieEx.addWord("50");
        prefixCard.put("50", "Maestro");
        for (int i = 56; i <= 69; i++) {
            trieEx.addWord(String.valueOf(i));
            prefixCard.put(String.valueOf(i), "Maestro");
        }
        cardLengths.put("Maestro", Arrays.asList(12, 13, 14, 15, 16, 17, 18, 19));
    }

    public String detectNetwork(String cardNumber) {
        if (prefixCard.isEmpty()) init();

        if (cardNumber.length() == 0) return null;

        StringBuilder builder = new StringBuilder();
        List<String> matchedPrefixes = new ArrayList<>();
        trieEx.lookupPrefix(builder, matchedPrefixes, cardNumber, 0);
        if (matchedPrefixes.isEmpty()) return null;

        String longestPrefix = builder.toString();
        int matchedLen = matchedPrefixes.size();
        for (int i = matchedLen - 1; i >= 0; i--) {
            String lastMatchedPrefix = matchedPrefixes.get(i);

            String name = prefixCard.get(lastMatchedPrefix);
            if (name == null) continue;

            int len = cardNumber.length();
            Optional<Integer> opt = cardLengths.get(name).stream().filter(n -> n == len).findFirst();
            if (opt.isPresent()) {
                System.out.println(">>> matched prefix: " + lastMatchedPrefix + ", name: " + name + ", length: " + len);
                return name;
            }
        }

        return null;
    }
    // endregion

    // region solution 1
    private Map<String, String> map = new TreeMap<>();
    private HashMap<String, Set<Integer>> lengthMap = new HashMap<>();

    private Map<String, String> dupMap = new HashMap<>();

    public String detectNetwork0(String cardNumber) {

        if (cardNumber.length() == 0) return null;

        setup();

        int cardLength = cardNumber.length();
        for (String key : dupMap.keySet()) {
            if (cardNumber.startsWith(key)) {
                String nm = dupMap.get(key);
                Set<Integer> set = lengthMap.get(nm);
                if (set.contains(cardLength)) {
                    return nm;
                }
            }
        }

        for (String prefix : map.keySet()) {
            if (cardNumber.startsWith(prefix)) {
                String name = map.get(prefix);

                Set<Integer> lengths = lengthMap.get(name);
                if (lengths.contains(cardLength)) {
                    return name;
                }
            }
        }
        return null;

    }

    private void setup() {
        if (!map.isEmpty())
            return;

        map.put("34", "Amex");
        map.put("37", "Amex");
        map.put("38", "Diners");
        map.put("39", "Diners");
        map.put("4", "Visa");
        for (int i = 51; i < 56; i++) {
            map.put(String.valueOf(i), "MasterCard");
        }
        dupMap.put("6011", "Discover");
        for (int j = 622126; j <= 622925; j++) {
            dupMap.put(String.valueOf(j), "Discover");
        }
        for (int k = 644; k < 650; k++) {
            dupMap.put(String.valueOf(k), "Discover");
        }
        dupMap.put("65", "Discover");
        map.put("50", "Maestro");
        for (int m = 56; m < 70; m++) {
            map.put(String.valueOf(m), "Maestro");
        }

        HashSet<Integer> set = new HashSet<>();
        set.add(15);
        lengthMap.put("Amex", set);

        set = new HashSet<>();
        set.add(14);
        lengthMap.put("Diners", set);

        set = new HashSet<>();
        set.add(13);
        set.add(16);
        set.add(19);
        lengthMap.put("Visa", set);

        set = new HashSet<>();
        set.add(16);
        lengthMap.put("MasterCard", set);

        set = new HashSet<>();
        set.add(16);
        set.add(19);
        lengthMap.put("Discover", set);

        set = new HashSet<>();
        for (int n = 12; n < 20; n++) {
            set.add(n);
        }
        lengthMap.put("Maestro", set);
    }
    // endregion
}
