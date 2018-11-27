package org.sean.array;

public class RotatedStringFinder {
    private boolean isSubstring(String full, String segment) {
        return full.contains(segment);
    }

    public boolean isRotate(String src, String target) {
        if (src == null || target == null)
            return false;

        if (src.length() != target.length())
            return false;

        String mixed = src + src;
        boolean isRotated = isSubstring(mixed, target);

        return isRotated;
    }
}
