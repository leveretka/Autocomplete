package ua.kotlin.demo.tries

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RWayTrieTest {
    val rWayTrie = RWayTrie()

    @Test
    fun shouldReturnZeroSizeForEmptyTrie() {
        assertEquals(0, rWayTrie.size)
    }

    @Test
    fun shouldIncrementSizeWhenWordAdded() {
        val size = rWayTrie.size
        rWayTrie.add("a" to 1)
        assertEquals(size + 1, rWayTrie.size)
    }

    @Test
    fun shouldFindAddedWord() {
        rWayTrie.add("a" to 1)
        assertTrue (rWayTrie.contains("a"))
    }

    @Test
    fun shouldNotFindAbsentWord() {
        rWayTrie.add("a" to 1)
        assertFalse (rWayTrie.contains("aa"))
    }
}