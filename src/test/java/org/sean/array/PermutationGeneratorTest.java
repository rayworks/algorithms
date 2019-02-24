package org.sean.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PermutationGeneratorTest {
    private PermutationGenerator pg;

    @Before
    public void setup() {
        pg = new PermutationGenerator();
    }

    @Test
    public void generateEmpty(){
        Assert.assertTrue(pg.generate(null, 0).isEmpty());
    }

    @Test
    public void generate() {
        List<String> list = pg.generate("a", 0);
        Assert.assertArrayEquals(new String[]{"a"}, list.toArray());

        list = pg.generate("ab", 0);
        Assert.assertEquals(2, list.size());

        list = pg.generate("abc", 0);
        System.out.println(list);
        Assert.assertNotNull(list);
        Assert.assertEquals(6, list.size());

        list = pg.generate("abcd", 0);
        Assert.assertEquals(24, list.size());
    }
}