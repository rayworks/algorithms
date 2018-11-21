package org.sean.array;

public class StringCompression {
    public String compress(String src) {
        if (src == null || src.length() < 1) {
            return src;
        }

        StringBuilder builder = new StringBuilder();
        char ch;
        char lastChar = ' ';

        int charCnt = 0;
        int count = src.length();
        for (int i = 0; i < count; i++) {
            ch = src.charAt(i);
            if (i == 0) {
                lastChar = ch;
            }
            if (ch == lastChar) {
                ++charCnt;
            } else {
                builder.append(lastChar).append(charCnt);

                lastChar = ch;
                charCnt = 1;
            }
        }

        builder.append(lastChar).append(charCnt);

        if (builder.length() < count)
            return builder.toString();
        else
            return src;
    }
}
