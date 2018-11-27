package org.sean.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EditDistanceResolverTest {

    private EditDistanceResolver resolver;

    @Before
    public void setup() {
        resolver = new EditDistanceResolver();
    }

    @Test
    public void isOneEditAway() {

        Assert.assertTrue(resolver.isOneEditAway("pale", "ple"));
        Assert.assertTrue(resolver.isOneEditAway("pales", "pale"));
        Assert.assertTrue(resolver.isOneEditAway("pale", "bale"));
        Assert.assertFalse(resolver.isOneEditAway("pale", "bake"));

    }
}