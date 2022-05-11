package org.sean.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HuffmanCodecTest {
    private HuffmanCodec codec;

    @Before
    public void setUp() throws Exception {
        codec = new HuffmanCodec();
    }

    @Test
    public void decode() {
        String raw = "aaaaa aaaaa eeee e e e e e e e e e e eiiiiiiiiiiiissstttt\r";
        String encoded = codec.encode(raw);
        System.out.println("encoded as :" + encoded);

        String actual = codec.decode(encoded);
        assertEquals("Huffman codec works", raw, actual);
    }
}