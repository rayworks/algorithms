package org.sean.array;

import java.util.ArrayList;
import java.util.List;

/** 12. Integer to Roman */
public class IntRomanConverter {
    public String intToRoman(int num) {
        int base = 1;
        StringBuilder builder = new StringBuilder();
        List<String> list = new ArrayList<>();
        while (num > 0) {
            int dlt = num % 10;

            list.add(basedToStr(dlt, base));

            base *= 10;
            num = num / 10;
        }

        int size = list.size();
        for (int i = size - 1; i >= 0; i--) {
            builder.append(list.get(i));
        }

        return builder.toString();
    }

    private String nChar(String ch, int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(ch);
        }
        return builder.toString();
    }

    String[] midChars1 = new String[] {"I", "V", "X"};
    String[] midChars10 = new String[] {"X", "L", "C"};
    String[] midChars100 = new String[] {"C", "D", "M"};
    String[] midChars1000 = new String[] {"M", "", ""};

    private String basedToStr(int i, int base) {
        String[] chars = new String[3];
        switch (base) {
            case 1:
                chars = midChars1;
                break;
            case 10:
                chars = midChars10;
                break;
            case 100:
                chars = midChars100;
                break;
            case 1000:
                chars = midChars1000;
                break;
        }

        if (i > 0 && i <= 3) return nChar(chars[0], i);
        else if (i == 4) return chars[0] + chars[1]; // "XL";
        else if (i == 5) return chars[1];
        else if (i > 5 && i < 9) {
            return chars[1] + nChar(chars[0], i - 5);
        } else if (i == 9) {
            return chars[0] + chars[2];
        }
        return "";
    }
}
