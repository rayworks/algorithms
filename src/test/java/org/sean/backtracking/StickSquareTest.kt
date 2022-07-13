package org.sean.backtracking

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class StickSquareTest {
    lateinit var square: StickSquare

    @Before
    fun setUp() {
        square = StickSquare()
    }

    @Test
    fun makesquare() {
        assert(square.makesquare(intArrayOf(5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3)))
    }
}