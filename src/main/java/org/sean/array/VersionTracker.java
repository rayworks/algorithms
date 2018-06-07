package org.sean.array;

public class VersionTracker extends VersionControl {
    private int[] array = new int[50];

    public VersionTracker(int[] array) {
        this.array = array;
    }

    public VersionTracker() {
    }

    /***
     * 1420736637 versions
     1150769282 is the first bad version.

     921239930 versions
     823161944 is the first bad version.
     * @param version
     * @return
     */

    @Override
    public boolean isBadVersion(int version) {
        return version >= 823161944;
    }

    public int firstBadVersion(int n) { // N versions
        int l = 1;
        int r = n;

        int m;
        boolean fromRight = false;
        boolean badVersion = false;
        while (r > l) {
            m = l + (r - l) / 2;

            System.out.println(String.format(">>> l: %d | mid: %d | r : %d", l, m, r));

            badVersion = isBadVersion(m);
            if (badVersion) {
                r = m - 1;
                fromRight = true;

                if (m > 1) {
                    if (!isBadVersion(m - 1)) {
                        return m;
                    }
                }

                if (l == r) {
                    r += 1;
                    break;
                }
            } else {
                l = m + 1;
                fromRight = false;

                // a step forward
                if (l != r) {
                    if (isBadVersion(l)) {
                        return l;
                    }
                }

                if (l == r) {
                    l -= 1;
                    break;
                }
            }
        }

        if (!fromRight)
            return l + 1;
        else
            return r;

    }

    //int a =Integer.MAX_VALUE;
}
