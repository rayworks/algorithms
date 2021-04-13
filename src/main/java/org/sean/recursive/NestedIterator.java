package org.sean.recursive;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/***
 * 341. Flatten Nested List Iterator
 */
public class NestedIterator implements Iterator<Integer> {

    private final LinkedList<Integer> flatList = new LinkedList<>();
    Iterator<Integer> iterator;

    public NestedIterator(List<NestedInteger> nestedList) {
        for (NestedInteger ni : nestedList) {
            extracted(ni);
        }
        iterator = flatList.iterator();
    }

    private void extracted(NestedInteger ni) {
        if (ni.isInteger()) {
            flatList.add(ni.getInteger());
        } else {
            for (NestedInteger e : ni.getList()) {
                extracted(e);
            }
        }
    }

    @Override
    public Integer next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}
