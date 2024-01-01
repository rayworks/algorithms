package org.sean.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class AccountMergerTest {

    private AccountMerger merger;

    @Before
    public void setup() {
        merger = new AccountMerger();
    }

    @Test
    public void accountsMerge() {
        List<List<String>> accounts = Arrays.asList(
                Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                Arrays.asList("Mary", "mary@mail.com"),
                Arrays.asList("John", "johnnybravo@mail.com")
        );
        List<List<String>> output = merger.accountsMerge(accounts);

        assertArrayEquals(output.get(0).toArray(),
                Arrays.asList("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com").toArray());

        assertArrayEquals(output.get(1).toArray(),
                Arrays.asList("Mary", "mary@mail.com").toArray()
        );

        assertArrayEquals(output.get(2).toArray(),
                Arrays.asList("John", "johnnybravo@mail.com").toArray()
        );
    }
}