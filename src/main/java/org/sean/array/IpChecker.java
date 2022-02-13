package org.sean.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/***
 * 93. Restore IP Addresses
 */
public class IpChecker {
    // region DFS
    private final List<String> out = new ArrayList<>();
    private boolean isValid(String segment) {
        if (segment.isEmpty() || segment.length() > 1 && segment.startsWith("0")) return false;
        int num = Integer.parseInt(segment);
        return num < 256;
    }

    private void restore(String s, int startPos, LinkedList<String> address) {
        int size = address.size();
        int len = s.length();
        if (size == 3) {
            if (startPos >= len - 3) {
                String segment = s.substring(startPos);
                if (isValid(segment)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String value : address) {
                        stringBuilder.append(value);
                        stringBuilder.append('.');
                    }
                    stringBuilder.append(segment);

                    out.add(stringBuilder.toString());
                }
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (startPos + i >= len) continue;

            String addr = s.substring(startPos, startPos + i + 1);
            if (!isValid(addr)) {
                continue;
            }
            address.add(addr);
            restore(s, startPos + i + 1, address);
            address.removeLast();
        }
    }

    public List<String> restoreIpAddresses(String s) {
        int length = s.length();
        if (length < 4 || length > 12) return Collections.emptyList();

        out.clear();
        restore(s, 0, new LinkedList<>());

        return out;
    }
    // endregion

    // region iterative
    public List<String> restoreIpAddresses0(String s) {
        int length = s.length();
        if (length < 4 || length > 12)
            return Collections.emptyList();

        List<String> out = new ArrayList<>();
        int i1, i2, i3, i4;
        for (int i = 1; i <= 3; i++) {
            String fir = s.substring(0, i);
            if (fir.length() > 1 && fir.charAt(0) == '0')
                break;
            i1 = Integer.parseInt(fir);
            if (i1 >= 256)
                break;

            for (int j = i + 1; j <= i + 3 && j <= length - 2; j++) { // 2 digits left for last 2 parts
                String sec = s.substring(i, j);
                if (sec.length() > 1 && sec.charAt(0) == '0')
                    break;
                i2 = Integer.parseInt(sec);
                if (i2 >= 256)
                    break;

                for (int k = j + 1; k <= j + 3 && k <= length - 1; k++) {
                    String thr = s.substring(j, k);
                    if (thr.length() > 1 && thr.charAt(0) == '0')
                        break;

                    i3 = Integer.parseInt(thr);
                    if (i3 >= 256)
                        break;

                    String four = s.substring(k);
                    if(four.length() < 1 || four.length() > 3)
                        continue;
                    if (four.length() > 1 && four.charAt(0) == '0')
                        continue;

                    i4 = Integer.parseInt(four);
                    if (i4 >= 256)
                        continue;

                    String n = i1 + "." + i2 + "." + i3 + "." + i4;
                    out.add(n);
                }
            }
        }

        return out;
    }
    //endregion
}
