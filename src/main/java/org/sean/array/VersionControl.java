package org.sean.array;

public class VersionControl {

    public boolean isBadVersion(int version) {
        if (version >= 37) {
            return true;
        }

        return false;
    }
}
