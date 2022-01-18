package org.sean.array;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class IpCheckerTest {

    private IpChecker ipChecker;

    @Before
    public void setUp() throws Exception {
        ipChecker = new IpChecker();
    }

    @Test
    public void restoreIpAddresses() {
        assertEquals(Arrays.asList("255.255.11.135", "255.255.111.35"), ipChecker.restoreIpAddresses("25525511135"));
        assertEquals(Arrays.asList("0.0.0.0"), ipChecker.restoreIpAddresses("0000"));
        assertEquals(Arrays.asList("1.0.10.23", "1.0.102.3", "10.1.0.23", "10.10.2.3", "101.0.2.3"),
                ipChecker.restoreIpAddresses("101023"));
    }
}
