package org.sean.backtracking

import java.util.*

/***
 * 473. Matchsticks to Square
 */
class StickSquare {
    private lateinit var visited: BooleanArray

    private fun makesquareHelper(edges: Int, nums: IntArray, target: Int, sum: Int, pos: Int): Boolean {
        if (edges == 4) {
            return true
        }
        if (edges > 4) {
            return false
        }
        if (sum == target) return makesquareHelper(edges + 1, nums, target, 0, 0)

        for (i in pos until nums.size) {
            if (visited[i]) continue
            if (sum + nums[i] > target) continue

            visited[i] = true
            if (makesquareHelper(edges, nums, target, sum + nums[i], i + 1)) {
                return true
            }
            visited[i] = false
        }
        return false
    }

    /***
     * The problem is very similar to [SubsetPartition]
     */
    fun makesquare(matchsticks: IntArray): Boolean {
        if (matchsticks.size < 4) return false
        val sum = Arrays.stream(matchsticks).sum()
        if (sum % 4 != 0) return false
        val edgeLen = sum / 4
        if (Arrays.stream(matchsticks).max().asInt > edgeLen) return false
        visited = BooleanArray(matchsticks.size)
        Arrays.sort(matchsticks)

        return makesquareHelper(0, matchsticks, edgeLen, 0, 0)
    }
}